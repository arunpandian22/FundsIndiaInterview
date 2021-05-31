 package `in`.fundsindia.interviewsample.domain.model.request
import com.google.gson.annotations.SerializedName

 /**
  * a data class for the having network request data
  */
 data class MoviesRequest(

	@field:SerializedName("api_key")
	val apiKey: String,

	@field:SerializedName("language")
	val language: String,

	@field:SerializedName("page")
	val page: String
)