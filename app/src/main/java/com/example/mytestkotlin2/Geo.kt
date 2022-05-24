package com.example.mytestkotlin2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Geo {
    @SerializedName("lat")
    @Expose
    var lat: String? = null

    @SerializedName("lng")
    @Expose
    var lng: String? = null
}