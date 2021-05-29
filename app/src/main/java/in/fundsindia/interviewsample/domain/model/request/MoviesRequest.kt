 package `in`.fundsindia.interviewsample.domain.model.request
import com.google.gson.annotations.SerializedName

 data class MoviesRequest(

	@field:SerializedName("api_key")
	val apiKey: String? = null,

	@field:SerializedName("language")
	val language: String? = null,

	@field:SerializedName("page")
	val page: String? = null
)