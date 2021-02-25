package com.qdd.mvxdemo.mvvm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.network.MVXNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

object Repository {

    fun login(user: User): LiveData<Result<String>> = fire(Dispatchers.IO) {
        val loginResponse = MVXNetwork.networkLogin(user)
        Log.e("Repository", "Repository.login...")
        if (loginResponse.status == "ok") {
            Log.e("Repository", "success status is ${loginResponse.status}")
            Result.success(loginResponse.result)
        } else {
            Result.failure(RuntimeException("response is ${loginResponse.status}"))
        }
    }

    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData(context){
            val result = try {
                block()
            }catch (e: Exception){
                Result.failure<T>(e)
            }
            Log.e("Repository","result is ${result.getOrNull()}")
            emit(result)
        }

    fun loginOld(user: User) = liveData(Dispatchers.IO){
        val result : Result<String> = try {
            val loginResponse = MVXNetwork.networkLogin(user)
            if (loginResponse.status == "ok"){
                Result.success(loginResponse.result)
            }else{
                Result.failure(RuntimeException(
                    "response is ${loginResponse.status}"))
            }
        }catch (e: Exception){
            Result.failure(e)
        }
        emit(result)
    }

}