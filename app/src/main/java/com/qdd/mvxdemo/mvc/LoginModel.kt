package com.qdd.mvxdemo.mvc

import com.qdd.mvxdemo.bean.LoginResponse
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.network.MVXNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginModel(private val activity: LoginMvcActivity) {

    fun login(user: User){
        activity.showProgressBar()
        MVXNetwork.loginService.login(user.name,user.pwd)
            .enqueue(object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    activity.showFailToast()
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    activity.loginToMain()
                }
            })
    }
}
