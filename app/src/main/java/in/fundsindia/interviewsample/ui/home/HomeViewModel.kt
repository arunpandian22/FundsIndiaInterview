package  `in`.fundsindia.interviewsample.ui.home
import `in`.fundsindia.interviewsample.domain.model.request.MoviesRequest
import `in`.fundsindia.interviewsample.domain.model.response.ErrorModel
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.domain.model.response.MovieListResponse
import `in`.fundsindia.interviewsample.domain.usecase.InsertMovieUseCase
import `in`.fundsindia.interviewsample.domain.usecase.base.GetMoviesUsecase
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject

/**
 * A viewmodel class for the having business logic and data fetching methods for the HomeFragment
 */
class HomeViewModel @Inject constructor(
    private val movieUseCase: GetMoviesUsecase,private val insertMovieUseCase: InsertMovieUseCase
): ViewModel() {


    val TAG="HomeViewModel"

    val movieListResponse: MutableLiveData<MovieListResponse> by lazy { MutableLiveData<MovieListResponse>() }
    val error: MutableLiveData<ErrorModel> by lazy { MutableLiveData<ErrorModel>() }




    fun getMovies(page:Int){
        movieUseCase.execute(MoviesRequest("8a03975d504c762ab63b6c5fa98e3c17","en-US",""+page)) {
            onComplete {
                Log.d(TAG, "res: " + it.totalPages)

                movieListResponse.value = it
//             insertMovies(it.results as MutableList<Movie>)
            }
            onCancel {
                Log.d(TAG, "cancel : ")
                error.value = ErrorModel("" + it.message, 0, "")

            }
            onError {
                error.value = it
                Log.d(TAG, "error: " + it.errorResponse)
            }

        }
    }

    fun insertMovies(movieList: MutableList<Movie>){
        insertMovieUseCase.execute(movieList) {
            onSucess {
                Log.d(TAG, "onSuccess : ")
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