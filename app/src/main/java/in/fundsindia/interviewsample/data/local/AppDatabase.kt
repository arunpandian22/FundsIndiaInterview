package `in`.fundsindia.interviewsample.data.local

import `in`.fundsindia.interviewsample.domain.model.response.Movie
import android.app.Instrumentation
import androidx.room.Database
import androidx.room.Room.inMemoryDatabaseBuilder
import androidx.room.RoomDatabase
import com.android.artgallery.data.source.local.dao.MovieDao


/**
 * To manage data items that can be accessed, updated
 * & maintain relationships between them
 *
 */
@Database(entities = [Movie::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {



    abstract val movieDao: MovieDao

    companion object {
        const val DB_NAME = "MovieListDB.db"
        private var instance: AppDatabase? = null
        open fun getTestAppDatabase(context: Instrumentation): AppDatabase? {
            if (instance == null) {
                instance = context?.let { inMemoryDatabaseBuilder(it.context, AppDatabase::class.java).allowMainThreadQueries().build() }
            }
            return instance
        }
    }


}