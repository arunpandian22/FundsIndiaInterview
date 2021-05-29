package `in`.fundsindia.interviewsample.di.component
import `in`.fundsindia.interviewsample.ui.MainActivityComponent
import `in`.fundsindia.interviewsample.ui.MainViewModel
import dagger.Module
import dagger.Provides




@Module(subcomponents = [MainActivityComponent::class])
class SubComponents
{
    @Provides
    fun provideMainViewModel(): MainViewModel {
        return MainViewModel()
    }
}

