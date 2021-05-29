package `in`.fundsindia.interviewsample.domain.usecase

import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository
import `in`.fundsindia.interviewsample.domain.usecase.base.BaseDbUsecase
import javax.inject.Inject

class InsertMovieUseCase @Inject constructor(private  val movieDBRepository: MovieDBRepository) : BaseDbUsecase<MutableList<Movie>,Unit>() {
    override suspend fun executeOnBackground(request: MutableList<Movie>): Unit {
        return  movieDBRepository.insertMovies( request)
    }

}
