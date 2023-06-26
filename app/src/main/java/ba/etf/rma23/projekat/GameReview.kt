package ba.etf.rma23.projekat

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class GameReview (
    @PrimaryKey(autoGenerate = true) @SerializedName("id") var id: Long,
    @ColumnInfo(name = "rating") @SerializedName("rating")  var rating: Int?,
    @ColumnInfo(name = "review") @SerializedName("review")  var review: String?,
    @ColumnInfo(name = "igdb_id") @SerializedName("igdb_id")  var igdb_id: Int,
    @ColumnInfo(name = "online") @SerializedName("online")  var online: Boolean,
    @ColumnInfo(name = "student") @SerializedName("student")  var student: String,
    @ColumnInfo(name = "timestamp") @SerializedName("timestamp")  var timestamp: String,
    )