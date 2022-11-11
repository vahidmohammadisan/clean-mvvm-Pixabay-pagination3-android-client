package info.vahidmohammadi.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "Images")
data class Image(
    @PrimaryKey(autoGenerate = true)
    var pk: Long = 0,
    @SerializedName("id")
    var id: Int = 0,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("tags")
    val tags: String?,
    @SerializedName("user")
    val user: String?,
    @SerializedName("previewURL")
    val previewURL: String?,
    @SerializedName("largeImageURL")
    val largeImageURL: String?,
    @SerializedName("imageURL")
    val imageURL: String?,
    @SerializedName("pageURL")
    val pageURL: String?,
) : Serializable