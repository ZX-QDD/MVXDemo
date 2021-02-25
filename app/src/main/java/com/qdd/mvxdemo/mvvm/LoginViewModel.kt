package com.qdd.mvxdemo.mvvm

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.qdd.mvxdemo.bean.User

class LoginViewModel: ViewModel(){

    private val loginLiveData = MutableLiveData<User>()

    val loginResultLiveData = Transformations.switchMap(loginLiveData){ user ->
        Log.d("LoginViewModel","loginResult...")
        Repository.login(user)
    }

    fun login(user: User){
        loginLiveData.value = user
    }

}