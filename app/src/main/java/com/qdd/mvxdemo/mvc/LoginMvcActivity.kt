package com.qdd.mvxdemo.mvc

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.MainActivity
import com.qdd.mvxdemo.R
import kotlinx.android.synthetic.main.activity_login_mvc.*

class LoginMvcActivity : AppCompatActivity() {
    private val loginModel: LoginModel by lazy { LoginModel(this) }
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mvc)
        init()
    }

    private fun init(){
        btn_login.setOnClickListener {
            val user = User(edit_name.text.toString(),edit_pwd.text.toString())
            loginModel.login(user)
        }
    }

    fun showProgressBar() {
        progressDialog.setTitle("正在登陆")
        progressDialog.show()
    }

    fun loginToMain() {
        startActivity(Intent(this@LoginMvcActivity, MainActivity::class.java))
        progressDialog.dismiss()
//        finish()
    }

    fun showFailToast() {
        Toast.makeText(this@LoginMvcActivity, "登陆失败", Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }
}