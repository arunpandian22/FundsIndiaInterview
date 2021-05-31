package `in`.fundsindia.interviewsample.ui.adapter
import `in`.fundsindia.interviewsample.R
import `in`.fundsindia.interviewsample.domain.model.response.Movie
import `in`.fundsindia.interviewsample.utils.GeneralConstant
import `in`.fundsindia.interviewsample.utils.colourPalletUtil.ColourPalleteUtil
import `in`.fundsindia.interviewsample.utils.colourPalletUtil.PalleteColourListener
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.Nullable
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

/**
 * An Adapter class for the listing the movies in the recyclerview
 */
class RvMovieAdapter(var movieList: ArrayList<Movie>) : RecyclerView.Adapter<RvMovieAdapter.ViewHolder>() {

    var colourPalleteUtil: ColourPalleteUtil = ColourPalleteUtil()

    //this method is returning the view for each item in the list
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return ViewHolder(view)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(movieList[position], colourPalleteUtil)
    }

    //this method is giving the size of the list
    override fun getItemCount(): Int {
        return movieList.size
    }

    //the class is hodling the list view
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(
            movieResponse: Movie, palleteUtil: ColourPalleteUtil
        ) {
           var ivMoviePoster = itemView.findViewById<ImageView>(R.id.ivMoviePoster)
           var viewTitleBackground = itemView.findViewById<View>(R.id.viewTitleBackground)
           var tvMovieName = itemView.findViewById<TextView>(R.id.tvMovieName)
            tvMovieName.text = movieResponse.title

            val imagePosterUrl: String = GeneralConstant.BASE_POSTER_PATH + movieResponse.posterPath


            Glide.with(itemView.context).load(imagePosterUrl).diskCacheStrategy(DiskCacheStrategy.ALL)
                .addListener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        @Nullable e: GlideException?,
                        model: Any,
                        target: Target<Drawable>,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable,
                        model: Any,
                        target: Target<Drawable>,
                        dataSource: DataSource,
                        isFirstResource: Boolean
                    ): Boolean {

                        //if you want to convert the drawable to ImageView
                        val bitmapImage = (resource as BitmapDrawable).bitmap
                        palleteUtil.getImageColorData(
                            bitmapImage,
                            object : PalleteColourListener {


                                override fun getColurPallete(pallete: Palette?) {
                                    viewTitleBackground.setBackgroundColor(
                                        pallete?.getVibrantColor(
                                            pallete?.getLightVibrantColor(0)
                                        )!!
                                    )
                                }
                            })
                        return false
                    }
                }).into(ivMoviePoster)

        }
    }
}