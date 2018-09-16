package com.apkdv.autolight

import android.provider.Settings.System.SCREEN_BRIGHTNESS
import android.content.ContentResolver
import android.app.Activity
import android.content.Context
import android.provider.Settings
import android.provider.Settings.SettingNotFoundException
import android.widget.Toast


/**
 * Created by ZhaoShulin on 2018/9/16 下午7:14.
 * <br>
 * Desc:
 * <br>
 */
object Utils {
    fun getScreenBrightness(activity: Activity): Int {
        var nowBrightnessValue = 0
        val resolver = activity.contentResolver
        try {
            nowBrightnessValue = android.provider.Settings.System.getInt(resolver, Settings.System.SCREEN_BRIGHTNESS)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return nowBrightnessValue
    }

    //
    fun isAutoBrightness(aContentResolver: ContentResolver): Boolean {
        var automicBrightness = false
        try {
            automicBrightness = Settings.System.getInt(aContentResolver,
                    Settings.System.SCREEN_BRIGHTNESS_MODE) == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC
        } catch (e: SettingNotFoundException) {
            e.printStackTrace()
        }
        return automicBrightness
    }

    //开启自动亮度
    fun startAutoBrightness(aContentResolver: ContentResolver) {
        Settings.System.putInt(aContentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE,
                Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC)
    }

    //停止自动亮度
    fun stopAutoBrightness(aContentResolver: ContentResolver) {
        Settings.System.putInt(aContentResolver,
                Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL)
    }

}

fun String.show(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}