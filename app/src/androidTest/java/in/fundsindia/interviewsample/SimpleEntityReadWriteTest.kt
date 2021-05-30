import `in`.fundsindia.interviewsample.data.local.AppDatabase
import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.android.artgallery.data.source.local.dao.MovieDao
import org.hamcrest.CoreMatchers.equalTo
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SimpleEntityReadWriteTest {
    private lateinit var movieDao: MovieDao

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        movieDao = AppDatabase.getTestAppDatabase(InstrumentationRegistry.getInstrumentation())!!.movieDao


    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        AppDatabase.getTestAppDatabase(InstrumentationRegistry.getInstrumentation())!!.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {

    }
}