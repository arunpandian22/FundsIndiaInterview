package `in`.fundsindia.interviewsample.domain.repository

import `in`.fundsindia.interviewsample.domain.model.response.Movie

interface MovieDBRepository {

    fun searchMovies(search: String?): MutableList<Movie>
    fun insertMovies( movie:  MutableList<Movie> )

}