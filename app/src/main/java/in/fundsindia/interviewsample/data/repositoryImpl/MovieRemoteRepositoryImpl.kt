package `in`.fundsindia.interviewsample.data.repositoryImpl
import `in`.fundsindia.interviewsample.data.remote.ApiService
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository

/**
 * This repository is responsible for
 * fetching data[movies] from server or db
 * */
class MovieRemoteRepositoryImpl(
    private val apiService: ApiService
) : MovieRepository {


  suspend override  fun  getMovies(): MovieListResponse {
        return apiService.getMovieList("8a03975d504c762ab63b6c5fa98e3c17","en-US","1").await()
    }


}