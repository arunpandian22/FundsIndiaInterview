package `in`.fundsindia.interviewsample.domain.repository

import `in`.fundsindia.interviewsample.domain.model.response.Movie

/**
 * To make an interaction between [MovieDBRepositoryImpl] & DB realted use cases
 * */
interface MovieDBRepository {

    suspend  fun searchMovies(search: String?): MutableList<Movie>
    suspend  fun insertMovies( movie:  MutableList<Movie> )

}