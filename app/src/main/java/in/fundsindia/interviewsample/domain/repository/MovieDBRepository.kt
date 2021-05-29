package `in`.fundsindia.interviewsample.domain.repository

import `in`.fundsindia.interviewsample.domain.model.response.Movie

interface MovieDBRepository {

    suspend  fun searchMovies(search: String?): MutableList<Movie>
    suspend  fun insertMovies( movie:  MutableList<Movie> )

}