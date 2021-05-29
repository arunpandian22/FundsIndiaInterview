package `in`.fundsindia.interviewsample.domain.mapper
import `in`.fundsindia.interviewsample.domain.model.response.ErrorModel
import android.accounts.NetworkErrorException
import android.util.Log
import com.google.gson.Gson
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject

/**
 * A class to map the error response  to error models
 */
class ErrorMapper @Inject constructor(private val gson: Gson) {

    var TAG="ErrorMapper"
    fun mapToDomainErrorException(throwable: Throwable?): ErrorModel? {
        val errorModel: ErrorModel? = when (throwable) {

            // if throwable is an instance of HttpException
            // then attempt to parse error data from response body
            is HttpException -> {
                // handle UNAUTHORIZED situation (when token expired)

                throwable.response()?.let {
                    ErrorModel(throwable.message(),throwable.code(),
                        it.message())
                }

//                if (throwable.code() == 401) {
//                    `in`.fundsindia.interviewsample.domain.model.response.ErrorModel(ErrorStatus.UNAUTHORIZED)
//
//                } else {
//                    getHttpError(throwable.response().errorBody())
//                }
            }

            // handle api call timeout error
            is SocketTimeoutException -> {
                Log.d(TAG,"socket exception: "+throwable.cause)
                ErrorModel("Please check your internet connection",0,throwable.message.toString())
            }

            is NetworkErrorException -> {
                ErrorModel("Please check your internet connection",0,throwable.message.toString())
            }
            // handle connection error
            is IOException -> {
                ErrorModel("" +
                        "Please check your internet connection",0,throwable.message.toString())
            }

            is UnknownHostException -> {
                ErrorModel("Something went wrong please try again later",0,throwable.message.toString())
            }

            is Exception ->{
                ErrorModel("Something went wrong please try again later",0,throwable.message.toString())
            }
            else -> null
        }
        return errorModel
    }

    /**
     * attempts to parse http response body and get error data from it
     *
     * @param body retrofit response body
     * @return returns an instance of [ErrorModel] with parsed data or NOT_DEFINED status
     */
    private fun getHttpError(body: ResponseBody?): ErrorModel {
        return try {
            // use response body to get error detail
            val result = body?.string()
            Log.d("getHttpError", "getErrorMessage() called with: errorBody = [$result]")
            val json = Gson().fromJson(result, JsonObject::class.java)
            ErrorModel(  json.toString(), 400, body?.string().toString())
        } catch (e: Throwable) {
            e.printStackTrace()
            ErrorModel(e.message.toString())
        }

    }
}