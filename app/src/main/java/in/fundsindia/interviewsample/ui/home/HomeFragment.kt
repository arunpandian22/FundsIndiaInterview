package  `in`.fundsindia.interviewsample.ui.home
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import `in`.fundsindia.interviewsample.R
import `in`.fundsindia.interviewsample.ui.MainActivity
import android.content.Context
import android.util.Log
import javax.inject.Inject

class HomeFragment : Fragment() {
     @Inject
     lateinit var homeViewModel: HomeViewModel
    var TAG="HomeFragment"


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
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG,"HomeView model: "+homeViewModel)
        homeViewModel.getMovies()
    }
}