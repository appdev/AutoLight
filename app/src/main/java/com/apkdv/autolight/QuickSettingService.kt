package com.apkdv.autolight

import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService


/**
 * Created by ZhaoShulin on 2018/9/16 下午7:12.
 * <br>
 * Desc:
 * <br>
 */
class QuickSettingService: TileService() {
    override fun onClick() {
        super.onClick()
        if (Settings.System.canWrite(this)) {
            val tile = qsTile ?: return
            when (tile.state) {
                Tile.STATE_ACTIVE -> {
                    Utils.stopAutoBrightness(contentResolver)
                    //当前状态是开，设置状态为关闭.
                    tile.state = Tile.STATE_INACTIVE
                    //更新快速设置面板上的图块的颜色，状态为关.
                    tile.updateTile()
                }
                Tile.STATE_UNAVAILABLE -> {
                }
                Tile.STATE_INACTIVE -> {
                    Utils.startAutoBrightness(contentResolver)
                    //当前状态是关，设置状态为开.
                    tile.state = Tile.STATE_ACTIVE
                    //更新快速设置面板上的图块的颜色，状态为开.
                    tile.updateTile()
                }
                else -> {
                }
            }//do close somethings.
        }else{
            MainActivity.start(this)
        }

        //do open somethings.
    }

    // 打开下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
    //在TleAdded之后会调用一次
    override fun onStartListening() {
        val tile = qsTile ?: return
        if (Utils.isAutoBrightness(contentResolver)){
            tile.state = Tile.STATE_ACTIVE
            //更新快速设置面板上的图块的颜色，状态为开.
            tile.updateTile()
        }else{
            tile.state = Tile.STATE_INACTIVE
            //更新快速设置面板上的图块的颜色，状态为关.
            tile.updateTile()
        }
    }

    // 关闭下拉菜单的时候调用,当快速设置按钮并没有在编辑栏拖到设置栏中不会调用
    // 在onTileRemoved移除之前也会调用移除
    override fun onStopListening() {
    }


}