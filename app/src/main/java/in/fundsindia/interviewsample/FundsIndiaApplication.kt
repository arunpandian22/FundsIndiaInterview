package `in`.fundsindia.interviewsample

import `in`.fundsindia.interviewsample.di.component.ApplicationComponent
import `in`.fundsindia.interviewsample.di.component.DaggerApplicationComponent
import android.app.Application

class FundsIndiaApplication : Application() {

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: ApplicationComponent by lazy {
        initializeComponent()
    }
    override fun onCreate() {
        super.onCreate()
    }

    open fun initializeComponent(): ApplicationComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerApplicationComponent.factory().create(applicationContext)
    }
}