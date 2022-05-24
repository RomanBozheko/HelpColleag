package com.example.mytestkotlin2

import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface UserApi {
    @GET("users/{userId}")
    fun getUser(@Path("userId") userId: Int): Observable<User?>?

    @GET("users/{userId}/todos")
    fun getTask(@Path("userId") userId: Int): Observable<List<Task?>?>?
}