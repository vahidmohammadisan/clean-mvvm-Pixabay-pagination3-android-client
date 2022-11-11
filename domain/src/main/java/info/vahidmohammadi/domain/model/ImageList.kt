package info.vahidmohammadi.domain.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ImageList(
    @SerializedName("hits")
    val images: List<Image>,
) : Serializable