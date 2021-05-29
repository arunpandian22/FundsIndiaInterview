package `in`.fundsindia.interviewsample.domain.usecase.base

import `in`.fundsindia.interviewsample.domain.model.response.ErrorModel
import android.util.Log
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

typealias CompletionDbBlock<T> = BaseDbUsecase.Request<T>.() -> Unit

abstract  class BaseDbUsecase <R,T> {


    protected abstract suspend fun executeOnBackground(request:R) : T
    private var parentJob: Job?=null
    private var backgroundContext: CoroutineContext = Dispatchers.IO
    private var foregroundContext: CoroutineContext = Dispatchers.Main

   fun execute(request:R,block: CompletionDbBlock<T>) {

       val response = BaseDbUsecase.Request<T>().apply { block() }

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
           } catch (e: Exception) {

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
        private var onSuccess: ((T) -> Unit)? = null
        private var onError : ((Throwable) -> Unit)? = null
        private var onFinished : (() -> Unit)? = null

        fun onSucess(block: (T) -> Unit) {
            onSuccess = block
        }

        fun onError(block: (Throwable) -> Unit) {

            onError = block

        }

        fun onFinished(block: () -> Unit){
            onFinished = block
        }




        operator fun invoke(result: T) {
            onSuccess?.invoke(result)
        }

        operator fun invoke(error: Throwable) {
            onError?.invoke(error)
        }

        operator fun invoke() {
            onFinished?.invoke()
        }
    }


}