package com.android.artgallery.data.source.local.dao

import `in`.fundsindia.interviewsample.domain.model.response.Movie
import androidx.room.*


/**
 * it provides access to [Movie] underlying database
 * */
@Dao
interface MovieDao {



    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun loadAll(vararg movies: MutableList<Movie>)


    @Query("SELECT * FROM Movie WHERE title LIKE '%' || :search || '%'")
    fun searchMovies(search: String?): MutableList<Movie>
//
//    @Delete
//    fun delete(photo: Movie)
//
//    @Query("DELETE FROM Movie")
//    fun deleteAll()
//
//    @Query("SELECT * FROM Photo where id = :photoId")
//    fun loadOneByPhotoId(photoId: Long): Photo?
//
//    @Query("SELECT * FROM Photo where title = :photoTitle")
//    fun loadOneByPhotoTitle(photoTitle: String): Photo?
//
//    @Update
//    fun update(photo: Photo)

}