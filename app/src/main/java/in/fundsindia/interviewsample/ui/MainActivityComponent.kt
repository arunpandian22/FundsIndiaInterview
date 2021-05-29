package `in`.fundsindia.interviewsample.ui

import `in`.fundsindia.interviewsample.di.qualifier.ActivityScope
import `in`.fundsindia.interviewsample.ui.dashboard.SearchFragment
import `in`.fundsindia.interviewsample.ui.home.HomeFragment
import dagger.Subcomponent

/**
 * Scope annotation that the MainActivityComponent uses
 * Classes annotated with @ActivityScope will have a unique instance in this Component
 * @author Arun Pandian M
 */
@ActivityScope
@Subcomponent
interface MainActivityComponent {
    // Factory to create instances of RegistrationComponent
    @Subcomponent.Factory
    interface Factory {
        fun create(): MainActivityComponent
    }

    // Classes that can be injected by this Component
    fun inject(activity: MainActivity)
    fun inject(fragment: SearchFragment)
    fun inject(fragment: HomeFragment)
}