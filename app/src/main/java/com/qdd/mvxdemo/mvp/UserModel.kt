package com.qdd.mvxdemo.mvp

import com.qdd.mvxdemo.bean.LoginResponse
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.network.MVXNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Model层，持有Presenter层引用
 */

class UserModel(private val iUserPresenter: IUserPresenter): IUserModel{

    override fun login(user: User){
        iUserPresenter.loginProgress()
        MVXNetwork.loginService.login(user.name,user.pwd)
            .enqueue(object: Callback<LoginResponse> {
                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    iUserPresenter.loginFail()
                    t.printStackTrace()
                }

                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    iUserPresenter.loginSuccess()
                }
            })
    }
}