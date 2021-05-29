package `in`.fundsindia.interviewsample.domain.usecase.base

import `in`.fundsindia.interviewsample.domain.model.response.ErrorModel
import `in`.fundsindia.interviewsample.domain.mapper.ErrorMapper
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionBlock<T> = UseCase.Request<T>.() -> Unit

abstract class UseCase<R,T>(private val errorUtil: ErrorMapper) {

    private var parentJob: Job?=null
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main
    var tag="UseCase"

    protected abstract suspend fun executeOnBackground(request:R): T

    fun execute(request:R,block: CompletionBlock<T>) {
        val response = Request<T>().apply { block() }


        unsubscribe()
        parentJob = SupervisorJob()
        CoroutineScope(foregroundContext + parentJob as CompletableJob).launch {
            try {
                val result = withContext(backgroundContext) {
                    executeOnBackground(request)
                }
                response(result)
            } catch (cancellationException: CancellationException) {
                response(cancellationException)
                Log.d(tag,"error: "+cancellationException.localizedMessage)
            } catch (e: Exception) {
                Log.d(tag,"exception error: "+e.message)
                Log.d(tag,"exception error: "+e.localizedMessage +"  cause:"+e.cause)
                Log.d(tag,"exception traces: "+e.cause)
                val error = errorUtil.mapToDomainErrorException(e)

                error?.let {
                    response(error)
                }
            }
        }
    }


    fun unsubscribe() {
        parentJob?.apply {
            cancelChildren()
            cancel()
        }
    }



    class Request<T> {
        private var onComplete: ((T) -> Unit)? = null
        private var onError: ((ErrorModel) -> Unit)? = null
        private var onCancel: ((CancellationException) -> Unit)? = null

        fun onComplete(block: (T) -> Unit) {
            onComplete = block
        }

        fun onError(block: (ErrorModel) -> Unit) {

            onError = block

        }

        fun onCancel(block: (CancellationException) -> Unit) {
            onCancel = block
        }


        operator fun invoke(result: T) {
            onComplete?.invoke(result)
        }

        operator fun invoke(error: ErrorModel) {
            onError?.invoke(error)
        }

        operator fun invoke(error: CancellationException) {
            onCancel?.invoke(error)
        }
    }
}