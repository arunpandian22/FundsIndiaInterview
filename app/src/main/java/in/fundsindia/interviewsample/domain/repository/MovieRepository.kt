package `in`.fundsindia.interviewsample.domain.repository
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse

/**
 * To make an interaction between [MovieRepositoryImpl] & [GetMoviesUsecase]
 * */
interface MovieRepository {

  suspend  fun getMovies(moviesRequest: MoviesRequest): MovieListResponse
}