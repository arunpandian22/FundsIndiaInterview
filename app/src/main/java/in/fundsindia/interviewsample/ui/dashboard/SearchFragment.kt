package `in`.fundsindia.interviewsample.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import `in`.fundsindia.interviewsample.R
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.ui.MainActivity
import `in`.fundsindia.interviewsample.ui.adapter.RvMovieAdapter
import android.content.Context
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import javax.inject.Inject

class SearchFragment : Fragment() {

    @Inject
    lateinit var searchViewModel: SearchViewModel
    var TAG = "SearchFragment"
    lateinit var rvMovies: RecyclerView
    lateinit var svMovies: SearchView
    lateinit var tbSearch: Toolbar
    lateinit var rvMovieAddapter: RvMovieAdapter
    lateinit var movieRVlayoutManager: LinearLayoutManager


    override fun onAttach(context: Context) {
        super.onAttach(context)

        // Grabs the registrationComponent from the Activity and injects this Fragment
        (activity as MainActivity).mainActivityComponent.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)
        rvMovies = root.findViewById<RecyclerView>(R.id.rvMovieList)
        svMovies = root.findViewById<SearchView>(R.id.svMovies)
        tbSearch = root.findViewById<Toolbar>(R.id.tbSearch)

        return root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "Search viewModel: " + searchViewModel)
        setUpRecyclerView()
        searchViewSetup()
        searchViewModel.searchMovies("")

        searchViewModel.searchMovieList.observe(viewLifecycleOwner, {

            rvMovieAddapter.movieList = it as ArrayList<Movie>
            rvMovieAddapter.notifyDataSetChanged()

        })
    }

    fun searchViewSetup() {
        svMovies.setFocusable(true)
        svMovies.onActionViewExpanded()
        svMovies.requestFocusFromTouch()

        svMovies.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Log.d(TAG, "" + query)
                return true
            }

            override fun onQueryTextChange(searchKeyWord: String?): Boolean {
                Log.d(TAG, "Text: " + searchKeyWord)

                if (searchKeyWord != null) {
                    searchViewModel.searchMovies(searchKeyWord)
                }

                return true
            }

        })
    }


    fun setUpRecyclerView() {
        movieRVlayoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvMovieAddapter = RvMovieAdapter(ArrayList())
        rvMovies.adapter = rvMovieAddapter
        rvMovies.layoutManager = movieRVlayoutManager

    }
}