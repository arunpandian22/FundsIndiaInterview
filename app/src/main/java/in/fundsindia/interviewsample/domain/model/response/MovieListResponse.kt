package `in`.fundsindia.interviewsample.domain.model.response

import com.google.gson.annotations.SerializedName

/**
 * A data class used to parse the response from the Network call
 */
data class MovieListResponse(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<Movie?>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null
)


