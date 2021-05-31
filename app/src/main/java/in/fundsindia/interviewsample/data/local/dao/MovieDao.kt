package com.android.artgallery.data.source.local.dao
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import androidx.room.*


/**
 * it provides access to [Movie] underlying database
 * */
@Dao
interface MovieDao {


    /**
     * an abstract method for the insert all the movies into the DB
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun loadAll(movies: MutableList<Movie>)


    /**
     *  an abstract method for the search all the movies from the DB based on the keyword
     */
    @Query("SELECT * FROM Movie WHERE title LIKE '%' || :search || '%'")
    fun searchMovies(search: String?): MutableList<Movie>



    /**
     *  an abstract method created inorder to test the DB to know whether all the data are inserted or not
     */
    @Query("SELECT * FROM Movie")
    fun getAll(): List<Movie>

    /**
     *  an abstract method created inorder to test the DB to know whether the primary key row are properly replaced or not
     */
    @Query("SELECT * FROM Movie WHERE id = :movieId ")
    fun getMovieById(movieId: Int): Movie?


}