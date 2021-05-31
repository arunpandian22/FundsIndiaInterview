package `in`.fundsindia.interviewsample.ui
import `in`.fundsindia.interviewsample.FundsIndiaApplication
import `in`.fundsindia.interviewsample.R
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import javax.inject.Inject

/**
 * An Activity class for the Home screen with two fragments
 */
class MainActivity : AppCompatActivity() {

    val TAG= "MainActivity"
    // @Inject annotated fields will be provided by Dagger
    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var mainActivityComponent: MainActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {

        // Creates an instance of Login component by grabbing the factory from the app graph
        // and injects this activity to that Component
        mainActivityComponent =   (application as FundsIndiaApplication).appComponent.mainActivityComponent().create()
        mainActivityComponent.inject(this)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



//        Log.d(TAG,"mainViewModel "+mainViewModel+ " Debug DB  "+DebugDB.getAddressLog())

        val navView: BottomNavigationView = findViewById(R.id.nav_view)


        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}