package com.qdd.mvxdemo.network

import com.qdd.mvxdemo.bean.LoginResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LoginService {

    @GET("login")
    fun login(@Query("name") name: String, @Query("pwd") pwd: String): Call<LoginResponse>

}
