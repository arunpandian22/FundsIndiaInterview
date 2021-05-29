package  `in`.fundsindia.interviewsample.ui.home
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.usecase.base.GetMoviesUsecase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val movieUseCase: GetMoviesUsecase,
): ViewModel() {

    val TAG="HomeViewModel"

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    fun getMovies(){
        movieUseCase.execute(MoviesRequest("","",""),{
            onComplete {
                Log.d(TAG,"res: "+it.totalPages)
            }
            onCancel {

            }
            onError {
                Log.d(TAG,"error: "+it.errorResponse)
            }

        })
    }
}