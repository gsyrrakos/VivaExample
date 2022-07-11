package com.giorgosyrr.vivaexample.data.model


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class DataFromApi(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id"          ) var Id          : Int?    = null,
    @SerializedName("Name"        ) var Name        : String? = null,
    @SerializedName("Price"       ) var Price       : String? = null,
    @SerializedName("Thumbnail"   ) var Thumbnail   : String? = null,
    @SerializedName("Image"       ) var Image       : String? = null,
    @SerializedName("Description" ) var Description : String? = null,
)

