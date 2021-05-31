package `in`.fundsindia.interviewsample.di.component
import `in`.fundsindia.interviewsample.ui.MainActivityComponent
import `in`.fundsindia.interviewsample.ui.MainViewModel
import dagger.Module
import dagger.Provides




//
/**
 * An Definition of a Dagger component that adds info from the different  viewmodel module to the graph
 * here only one activity so MainActivityComponent only added
 */
@Module(subcomponents = [MainActivityComponent::class])
class SubComponents
{
    @Provides
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}

