package `in`.fundsindia.interviewsample.di.module
import `in`.fundsindia.interviewsample.di.qualifier.ViewModelKey
import `in`.fundsindia.interviewsample.ui.dashboard.SearchViewModel
import `in`.fundsindia.interviewsample.ui.home.HomeViewModel
import `in`.fundsindia.interviewsample.utils.ViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun homeViewModel(viewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    abstract fun searchViewModel(viewModel: SearchViewModel): ViewModel

}
