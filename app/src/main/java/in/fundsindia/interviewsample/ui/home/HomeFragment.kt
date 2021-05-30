package  `in`.fundsindia.interviewsample.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import `in`.fundsindia.interviewsample.R
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.ui.MainActivity
import `in`.fundsindia.interviewsample.ui.adapter.RvMovieAdapter
import `in`.fundsindia.interviewsample.utils.NetworkUtils
import `in`.fundsindia.interviewsample.utils.pagination.PaginationListener
import `in`.fundsindia.interviewsample.utils.pagination.PaginationListener.Companion.PAGE_START
import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class HomeFragment : Fragment() {
     @Inject
     lateinit var homeViewModel: HomeViewModel
    var TAG="HomeFragment"
    lateinit var rvMovies :RecyclerView
    lateinit var tbHome :Toolbar
    lateinit var tvError: TextView
    lateinit var movieRVlayoutManager: LinearLayoutManager
    private var currentPage: Int = PAGE_START
    private var isLastPageHere = false
    lateinit var rvMovieAddapter: RvMovieAdapter
    private var isLoadingHere = false




    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Grabs the registrationComponent from the Activity and injects this Fragment
        (activity as MainActivity).mainActivityComponent .inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_home, container, false)
        rvMovies  = root.findViewById(R.id.rvMovieList)
        tbHome  = root.findViewById(R.id.tbHome)
        tbHome.title = "Home"
        tvError = root.findViewById(R.id.tvError)
        tvError.visibility = View.GONE

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"HomeView model: "+homeViewModel)

        setUpRecyclerView()
        homeViewModel.movieListResponse.observe(viewLifecycleOwner,{

            var movieList: ArrayList<Movie> = it.results as ArrayList<Movie>
            tvError.visibility = View.GONE
            rvMovies.visibility = View.VISIBLE

            if (currentPage == 1) {
                rvMovieAddapter.movieList = movieList
                homeViewModel.insertMovies(movieList)
            }
            else
                rvMovieAddapter.movieList.addAll(movieList)

            rvMovieAddapter.notifyDataSetChanged()
            isLoadingHere = false
            if(currentPage > it.totalPages!! )
                isLastPageHere = true
        })
        homeViewModel.error.observe(viewLifecycleOwner,{
            isLoadingHere = false
            tvError.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
            tvError.text = getString(R.string.errorStateSomethingWentWrong)

        })
        if (context?.let { NetworkUtils.isConnected(it) } == true)
        homeViewModel.getMovies(currentPage)
        else{
            tvError.visibility = View.VISIBLE
            rvMovies.visibility = View.GONE
            tvError.text = getString(R.string.errorStateOffline)

        }
    }


    fun setUpRecyclerView()
    {
        movieRVlayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMovieAddapter= RvMovieAdapter(ArrayList())
        rvMovies.adapter= rvMovieAddapter
        rvMovies.layoutManager = movieRVlayoutManager
        setRecyclerListener()
    }


    /**
     * A method to set the recyclerView scroll listener
     */
    fun setRecyclerListener() {

        Log.d(TAG, "do more API call: outside" + currentPage)
        rvMovies.addOnScrollListener(object : PaginationListener(movieRVlayoutManager) {

            override fun loadMoreItems() {
                isLoadingHere = true
                currentPage++
                Log.d(TAG, "do more API call:" + currentPage )
//                doApiCall();
                homeViewModel.getMovies(currentPage)

            }

            override val isLastPage: Boolean
                get() = isLastPageHere
            override val isLoading: Boolean
                get() = isLoadingHere



        })

    }
}