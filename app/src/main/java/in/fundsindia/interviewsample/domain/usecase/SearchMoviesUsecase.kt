package `in`.fundsindia.interviewsample.domain.usecase

import `in`.fundsindia.interviewsample.domain.mapper.ErrorMapper
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository
import `in`.fundsindia.interviewsample.domain.usecase.base.BaseDbUsecase
import javax.inject.Inject

class SearchMoviesUsecase @Inject constructor(private  val movieDBRepository: MovieDBRepository) : BaseDbUsecase<String, MutableList<Movie>>()
{
    override suspend fun executeOnBackground(request: String): MutableList<Movie> {
       return movieDBRepository.searchMovies("a")
    }

}



