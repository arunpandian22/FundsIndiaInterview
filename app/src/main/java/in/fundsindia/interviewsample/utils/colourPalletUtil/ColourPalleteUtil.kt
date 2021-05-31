package `in`.fundsindia.interviewsample.utils.colourPalletUtil

import android.graphics.Bitmap
import androidx.palette.graphics.Palette

/**
 * A class used to get the primary colors from the image
 */
class ColourPalleteUtil {
    var palleteColourListener: PalleteColourListener? = null
    fun getImageColorData(bitmap: Bitmap?, palleteColourListener: PalleteColourListener) {
        this.palleteColourListener = palleteColourListener
        val builder: Palette.Builder? = bitmap?.let { Palette.from(it) }
        builder?.generate(object : Palette.PaletteAsyncListener {
            override  fun onGenerated(palette: Palette?) {
                palleteColourListener.getColurPallete(palette)
            }
        })
    }
}