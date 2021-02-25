package com.qdd.mvxdemo.mvvm

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.qdd.mvxdemo.bean.User
import com.qdd.mvxdemo.MainActivity
import com.qdd.mvxdemo.R
import kotlinx.android.synthetic.main.activity_login_mvvm.*

class LoginMvvmActivity : AppCompatActivity() {

    //绑定viewModel
    private val loginViewModel by lazy { ViewModelProvider(this)[LoginViewModel::class.java] }
    private val progressDialog: ProgressDialog by lazy { ProgressDialog(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_mvvm)
//        init()
        btn_login.setOnClickListener {
            val user = User(edit_name.text.toString(),edit_pwd.text.toString())
            loginViewModel.login(user)
            showProgressBar()
        }

        //监听LiveData
        loginViewModel.loginResultLiveData.observe(this, Observer {
            Log.e("MainActivity","observe...")
            val result = it.getOrNull()
            if (result.equals("登陆成功")){
                Toast.makeText(this@LoginMvvmActivity, result, Toast.LENGTH_SHORT).show()
                loginToMain()
            }else
                showFailToast()
        })
    }

    private fun showProgressBar() {
        progressDialog.setTitle("正在登陆")
        progressDialog.show()
    }

    private fun loginToMain() {
        startActivity(Intent(this@LoginMvvmActivity, MainActivity::class.java))
        progressDialog.dismiss()
//        finish()
    }

    private fun showFailToast() {
        Toast.makeText(this@LoginMvvmActivity, "登陆失败", Toast.LENGTH_SHORT).show()
        progressDialog.dismiss()
    }
}