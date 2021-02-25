package com.qdd.mvxdemo.network

import android.util.Log
import com.qdd.mvxdemo.bean.LoginResponse
import com.qdd.mvxdemo.bean.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object MVXNetwork {

    val loginService = ServiceCreator.create<LoginService>()

    suspend fun networkLogin(user: User): LoginResponse = loginService.login(user.name, user.pwd).await()

    private suspend fun <T> Call<T>.await(): T{
        Log.e("SWNetwork","await...")
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body =response.body()
                    if (body !=null)
                        continuation.resume(body)
                    else
                        continuation.resumeWithException(
                            RuntimeException("response body is null")
                        )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }
            })
        }

    }
}