package `in`.fundsindia.interviewsample.domain.model.response
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

/**
 * A data model class used to  create RoomDB table and parse the movie json object from the response
 */
@Entity(tableName = "Movie")
data class Movie(

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

    @PrimaryKey
	@field:SerializedName("id")
	val id: Int? = null,


)