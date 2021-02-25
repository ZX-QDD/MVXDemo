package com.qdd.mvxdemo.mvp

interface IUserView {

    /**
     * 显示进度条
     */
    fun showProgressBar()

    /**
     * 登陆成功跳主页
     */
    fun loginToMain()

    /**
     * 登陆失败Toast
     */
    fun showFailToast()

}