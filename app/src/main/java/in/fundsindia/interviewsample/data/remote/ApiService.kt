package `in`.fundsindia.interviewsample.data.remote
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * A interface has the abstract methods of the API calls
 */
interface ApiService {
    @GET("movie/popular")
    fun getMovieList(
            @Query("api_key")apiKey: String,
            @Query("language")language: String,
            @Query("page")page: String,
    ): Deferred<MovieListResponse>

}