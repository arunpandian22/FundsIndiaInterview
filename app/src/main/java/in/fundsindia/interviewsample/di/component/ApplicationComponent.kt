package `in`.fundsindia.interviewsample.di.component
import `in`.fundsindia.interviewsample.di.module.DatabaseModule
import `in`.fundsindia.interviewsample.di.module.NetworkModule
import `in`.fundsindia.interviewsample.ui.MainActivity
import `in`.fundsindia.interviewsample.ui.MainActivityComponent
import android.content.Context
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component( modules=  [SubComponents::class,NetworkModule::class, DatabaseModule::class] )

interface ApplicationComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        fun create(@BindsInstance context: Context): ApplicationComponent
    }

    // Types that can be retrieved from the graph
    fun mainActivityComponent(): MainActivityComponent.Factory

    fun inject(activity: MainActivity)


}