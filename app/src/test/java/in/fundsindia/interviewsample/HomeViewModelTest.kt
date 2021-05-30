package `in`.fundsindia.interviewsample

import `in`.fundsindia.interviewsample.domain.mapper.ErrorMapper
import `in`.fundsindia.interviewsample.domain.usecase.InsertMovieUseCase
import `in`.fundsindia.interviewsample.domain.usecase.base.GetMoviesUsecase
import `in`.fundsindia.interviewsample.ui.home.HomeViewModel
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * A Test case for HomeViewModelTest
 */
class HomeViewModelTest {
    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var viewModel: HomeViewModel

    lateinit var getMoviesUsecase: GetMoviesUsecase

    lateinit var insertMovieUseCase: InsertMovieUseCase


    @Before
    fun setup() {
//        getMoviesUsecase = GetMoviesUsecase(ErrorMapper(Gson()),)
//
//        viewModel = HomeViewModel( getMoviesUsecase,insertMovieUseCase)

    }

    @Test
    fun testgetMovies() {
        viewModel.getMovies(1)
        assertEquals(LiveDataTestUtil.getValue(viewModel.movieListResponse), "")
    }

}