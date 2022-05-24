package com.example.mytestkotlin2


import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class Presenter {
    val saveStateName = "saveName"
    val saveStateTask = "saveTask"
    val randomKey = "random"
    val nameKey = "name"
    val taskKey = "task"
    val btnRandTodo = "RANDOM TODO"
    val btnFirstTask = "TASK 1"
    val btnSecondTask = "TASK 2"

 
    private val uRL = "https://jsonplaceholder.typicode.com/"
    private var retrofitInstance: Retrofit? = null

    fun getRetrofitInstance(): Retrofit? {
        if (retrofitInstance == null) {
            retrofitInstance = Retrofit.Builder()
                .baseUrl(uRL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            
        }
        return retrofitInstance
    }
    
    //
//    fun <T> createRetrofitService(clazz: Class<T>?, endPoint: String?): T {
//        val restAdapter = Retrofit.Builder()
//            .baseUrl(endPoint)
//            .build()
//        return restAdapter.create(clazz)
//    }
}