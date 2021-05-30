package `in`.fundsindia.interviewsample.data.repositoryImpl
import `in`.fundsindia.interviewsample.data.remote.ApiService
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository

/**
 * This repository is responsible for
 * fetching data[movies] from server or db
 * */
class MovieRemoteRepositoryImpl(
    private val apiService: ApiService
) : MovieRepository {


  suspend override  fun  getMovies(moviesRequest: MoviesRequest): MovieListResponse {
        return apiService.getMovieList(moviesRequest.apiKey,moviesRequest.language,moviesRequest.page).await()
    }


}