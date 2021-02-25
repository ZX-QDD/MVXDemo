package com.qdd.mvxdemo.mvp

import com.qdd.mvxdemo.bean.User

/**
 * Presenter层，持有View层和Model层引用
 * 控制Model层执行登陆
 * 并通过Model返回更新View层界面
 */
class UserPresenter(private val userView: IUserView): IUserPresenter {

    private var userModel: IUserModel = UserModel(this)

    override fun login(user: User) {
        userModel.login(user)
    }

    override fun loginProgress() {
        userView.showProgressBar()
    }

    override fun loginSuccess() {
        userView.loginToMain()
    }

    override fun loginFail() {
        userView.showFailToast()
    }

}