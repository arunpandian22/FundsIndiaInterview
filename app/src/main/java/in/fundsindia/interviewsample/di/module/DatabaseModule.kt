package `in`.fundsindia.interviewsample.di.module
import `in`.fundsindia.interviewsample.data.local.AppDatabase
import `in`.fundsindia.interviewsample.data.remote.ApiService
import `in`.fundsindia.interviewsample.data.repositoryImpl.MovieDBRepositoryImpl
import `in`.fundsindia.interviewsample.data.repositoryImpl.MovieRemoteRepositoryImpl
import `in`.fundsindia.interviewsample.di.component.ApplicationComponent
import `in`.fundsindia.interviewsample.domain.repository.MovieDBRepository
import `in`.fundsindia.interviewsample.domain.repository.MovieRepository
import dagger.Provides
import androidx.room.Room
import android.app.Application
import android.content.Context
import com.android.artgallery.data.source.local.dao.MovieDao
import dagger.Binds
import dagger.Module

import javax.inject.Singleton


/**
 * A Dagger module class to  define the injecting objects related to Database
 */

@Module
class DatabaseModule {



    @Provides
    @Singleton
     fun provideAppDatabase( application: Context): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }



    @Singleton
    @Provides
     fun provideMovieDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao
    }





}