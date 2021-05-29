package `in`.fundsindia.interviewsample.di.module
import `in`.fundsindia.interviewsample.data.local.AppDatabase
import `in`.fundsindia.interviewsample.di.component.ApplicationComponent
import dagger.Provides
import androidx.room.Room
import android.app.Application
import com.android.artgallery.data.source.local.dao.MovieDao
import dagger.Module

import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    internal fun provideAppDatabase(application: Application): AppDatabase {
        return Room.databaseBuilder(
            application,
            AppDatabase::class.java,
            AppDatabase.DB_NAME
        ).allowMainThreadQueries().build()
    }


    @Provides
    internal fun providePhotoDao(appDatabase: AppDatabase): MovieDao {
        return appDatabase.movieDao
    }
}