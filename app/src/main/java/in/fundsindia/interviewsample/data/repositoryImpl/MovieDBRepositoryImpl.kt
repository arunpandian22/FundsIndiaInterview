package `in`.fundsindia.interviewsample.data.repositoryImpl

import `in`.fundsindia.interviewsample.data.local.AppDatabase
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository
import dagger.Provides
import javax.inject.Singleton

/**
 * This repository is responsible for
 * fetching or storing data[movies] from or to DB
 * */
class MovieDBRepositoryImpl(
    private val database: AppDatabase
) : MovieDBRepository {
    suspend override fun searchMovies(search: String?): MutableList<Movie> {
        return  database.movieDao.searchMovies(search)
    }

   suspend override  fun insertMovies( movie:  MutableList<Movie> ) {
        return  database.movieDao.loadAll(movie)
    }
}


