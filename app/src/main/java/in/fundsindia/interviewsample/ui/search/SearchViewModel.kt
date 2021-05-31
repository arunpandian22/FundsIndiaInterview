package  `in`.fundsindia.interviewsample.ui.search

import `in`.fundsindia.interviewsample.domain.model.response.ErrorModel
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.usecase.SearchMoviesUsecase
import `in`.fundsindia.interviewsample.domain.usecase.base.GetMoviesUsecase
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * A viewmodel class for the having business logic and data fetching methods for the SearchFragment
 */
class SearchViewModel  @Inject constructor(
        private val movieUseCase: GetMoviesUsecase, private val searchMoviesUsecase: SearchMoviesUsecase
): ViewModel() {

    val TAG ="SearchViewModel"

    val searchMovieList: MutableLiveData<MutableList<Movie>> by lazy { MutableLiveData<MutableList<Movie>>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }


    fun searchMovies(movieKeyWord: String){
        searchMoviesUsecase.execute(movieKeyWord) {
            onSucess {
                Log.d(TAG, "onSuccess : "+it.size)
                searchMovieList.value = it
            }
            onFinished {
                Log.d(TAG, "onFinished : ")

            }
            onError {
                Log.d(TAG, "onError : ")

            }
        }
    }
}