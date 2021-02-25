package com.qdd.mvxdemo.mvp

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.MainActivity
import com.qdd.mvxdemo.R
import kotlinx.android.synthetic.main.activity_login_mvp.*

/**
 * View层，通过持有的Presenter层引用进行逻辑操作
 */
class LoginMvpActivity : AppCompatActivity(),IUserView {

    private val mContext: Context by lazy { this }
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }
    private val mUserPresenter: UserPresenter by lazy { UserPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mvp)
        init()
    }

    private fun init(){
        btn_login.setOnClickListener {
            val user = User(edit_name.text.toString(),edit_pwd.text.toString())
            mUserPresenter.login(user)
        }
    }

    override fun showProgressBar() {
        progressDialog.setTitle("正在登陆")
        progressDialog.show()
    }

    override fun loginToMain() {
        startActivity(Intent(mContext, MainActivity::class.java))
        progressDialog.dismiss()
//        finish()
    }

    override fun showFailToast() {
        Toast.makeText(mContext, "登陆失败", Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }
}