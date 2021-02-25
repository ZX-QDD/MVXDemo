package com.qdd.mvxdemo.mvp

import com.qdd.mvxdemo.bean.User

interface IUserModel {
    /**
     * 登陆操作
     * @param user
     */
    fun login(user: User)
}