package com.apkdv.autolight

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import android.content.pm.PackageManager




class MainActivity : AppCompatActivity() {
    companion object {

        fun start(context: Context) {
            "请授权后使用".show(context)
            val intent = Intent(context, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            context.startActivity(intent)
        }

    }

    private val REQUEST_CODE_PERMISSIONS = 0x000321
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!Settings.System.canWrite(this)) {
            "需要授予权限才能运行".show(this@MainActivity)
            val intent = Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS,
                    Uri.parse("package:$packageName"))
            startActivityForResult(intent, REQUEST_CODE_PERMISSIONS)
        } else {
            "已经授权".show(this@MainActivity)
            finish()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            // 判断是否有WRITE_SETTINGS权限
            if (Settings.System.canWrite(this)) {
                "授权成功".show(this@MainActivity)
            } else {
                "授权失败".show(this@MainActivity)
            }
            finish()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
