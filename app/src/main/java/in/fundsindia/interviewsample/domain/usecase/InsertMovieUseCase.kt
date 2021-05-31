package `in`.fundsindia.interviewsample.domain.usecase

import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository
import `in`.fundsindia.interviewsample.domain.usecase.base.BaseDbUsecase
import javax.inject.Inject

/**
 * An interactor that calls the actual implementation of inserting the movies into the RoomDb (which is injected by DI)
 * it handles the insertion of the movies and invoking the success call backs
 */
class InsertMovieUseCase @Inject constructor(private  val movieDBRepository: MovieDBRepository) : BaseDbUsecase<MutableList<Movie>,Unit>() {
    override suspend fun executeOnBackground(request: MutableList<Movie>): Unit {
        return  movieDBRepository.insertMovies( request)
    }

}
