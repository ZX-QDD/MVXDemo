package com.qdd.mvxdemo.mvp

import com.qdd.mvxdemo.bean.User

interface IUserPresenter {

    /**
     * 登陆
     * @param user
     */
    fun login(user: User)

    /**
     * 正在登陆
     */
    fun loginProgress()

    /**
     * 登陆成功
     */
    fun loginSuccess()

    /**
     * 登陆失败
     */
    fun loginFail()

}