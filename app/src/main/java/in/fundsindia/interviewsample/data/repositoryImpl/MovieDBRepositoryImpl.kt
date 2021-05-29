package `in`.fundsindia.interviewsample.data.repositoryImpl

import `in`.fundsindia.interviewsample.data.local.AppDatabase
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository

class MovieDBRepositoryImpl(
    private val database: AppDatabase
) : MovieDBRepository {
    override fun searchMovies(search: String?): MutableList<Movie> {
        return  database.movieDao.searchMovies(search)
    }

    override fun insertMovies( movie:  MutableList<Movie> ) {
        return  database.movieDao.loadAll(movie)
    }
}