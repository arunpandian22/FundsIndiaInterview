import `in`.fundsindia.interviewsample.data.local.AppDatabase
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.artgallery.data.source.local.dao.MovieDao
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DataBaseQueryTest {
    private lateinit var movieDao: MovieDao

    @Before
    fun createDb() {
//        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDao = AppDatabase.getTestAppDatabase(InstrumentationRegistry.getInstrumentation())!!.movieDao
    }



    @After
    @Throws(IOException::class)
    fun closeDb() {
//        AppDatabase.getTestAppDatabase(InstrumentationRegistry.getInstrumentation())!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeMoviesAndCheckAllAreInserted() {
        var movieList: MutableList<Movie> = ArrayList()
        movieList.add(Movie("en","The Unholy","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieList.add(Movie("en","Army of the Dead","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632358))
        movieDao.loadAll(movieList)
        Assert.assertEquals( movieDao.searchMovies("").size, 2);
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieAndCheckCorrectlyInserted(){
        var movieList: MutableList<Movie> = ArrayList()
        movieList.add(Movie("en","22 vs. Earth","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieDao.loadAll(movieList)
        Assert.assertEquals( movieDao.searchMovies("22 vs. Earth").size, 1)
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieAndCheckSearchWorks(){
        var movieList: MutableList<Movie> = ArrayList()
        movieList.add(Movie("en","The Unholy","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieList.add(Movie("en","Army of the Dead","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632358))
        movieList.add(Movie("en","22 vs. Earth","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieDao.loadAll(movieList)
        Assert.assertEquals( movieDao.searchMovies("ar")[0].title?.toLowerCase()?.contains("ar"), true)
    }

    @Test
    @Throws(Exception::class)
    fun writeMovieAndCheckPrimaryKey(){
        // here is is primary key
        var movieList: MutableList<Movie> = ArrayList()
        movieList.add(Movie("en","The Unholy","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieList.add(Movie("en","Army of the Dead","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632358))
        movieList.add(Movie("en","22 vs. Earth","/4ryVgyGJzdBq8ejVIB0snxBqnyM.jpg",632357))
        movieDao.loadAll(movieList)
        Assert.assertEquals( movieDao.getMovieById(632357)?.title?.toLowerCase()?.equals("22 vs. Earth"), true)
    }


}