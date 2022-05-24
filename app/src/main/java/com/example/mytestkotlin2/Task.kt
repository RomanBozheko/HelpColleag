package com.example.mytestkotlin2

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Task {
    @SerializedName("userId")
    @Expose
    var userId: Int? = null

    @SerializedName("id")
    @Expose
    var id: Int? = null

    @SerializedName("title")
    @Expose
    var title: String? = null

    @SerializedName("completed")
    @Expose
    var completed: Boolean? = null
}