package `in`.fundsindia.interviewsample.domain.usecase.base

import `in`.fundsindia.interviewsample.domain.mapper.ErrorMapper
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository
import javax.inject.Inject



class GetMoviesUsecase@Inject constructor(errorUtil: ErrorMapper, private val movieRepository: MovieRepository) :
    UseCase<MoviesRequest, MovieListResponse>(errorUtil) {
    override suspend fun executeOnBackground(request: MoviesRequest): MovieListResponse {
        return movieRepository.getMovies(request)
    }
}