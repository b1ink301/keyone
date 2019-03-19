// Copyright 2016 Google Inc.
// 
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
// 
//      http://www.apache.org/licenses/LICENSE-2.0
// 
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package kr.b1ink.keyone

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.provider.Settings
import android.service.quicksettings.Tile
import android.service.quicksettings.TileService
import android.util.Log

/**
 * system = night_display_navi_brightness
 * global = night_mode_keyboard_brightness, adjusting_keyboard_brightness, auto_keyboard_brightness
 * adb shell pm grant kr.b1ink.keyone android.permission.WRITE_SECURE_SETTINGS
 */

@SuppressLint("Override")
@TargetApi(Build.VERSION_CODES.N)
class QuickSettingsService : TileService() {

    // Access storage to see how many times the tile
    // has been tapped.
    private val serviceStatus: Boolean
        get() {

//            val prefs = applicationContext
//                .getSharedPreferences(
//                    PREFERENCES_KEY,
//                    Context.MODE_PRIVATE
//                )
//
//            var isActive = prefs.getBoolean(SERVICE_STATUS_FLAG, false)
//            isActive = !isActive
//
//            prefs.edit().putBoolean(SERVICE_STATUS_FLAG, isActive).apply()

            return Settings.Global.getInt(applicationContext.contentResolver, "auto_keyboard_brightness") > 0
        }

    /**
     * Called when the tile is added to the Quick Settings.
     * @return TileService constant indicating tile state
     */

    override fun onTileAdded() {
        Log.d(TAG, "Tile added")
    }

    /**
     * Called when this tile begins listening for events.
     */
    override fun onStartListening() {
        Log.d(TAG, "Start listening")
    }

    /**
     * Called when the user taps the tile.
     */
    override fun onClick() {
        Log.d(TAG, "Tile tapped")

        updateTile()
    }

    /**
     * Called when this tile moves out of the listening state.
     */
    override fun onStopListening() {
        Log.d(TAG, "Stop Listening")
    }

    /**
     * Called when the user removes this tile from Quick Settings.
     */
    override fun onTileRemoved() {
        Log.d(TAG, "Tile removed")
    }

    // Changes the appearance of the tile.
    private fun updateTile() {

        val tile = this.qsTile
        val isActive = serviceStatus

//        val newIcon: Icon
//        val newLabel: String
        val newState: Int

        setAutoKeyboardBrightness(isActive)

        // Change the tile to match the service status.
        if (!isActive) {

//            newLabel = String.format(
//                Locale.US,
//                "%s %s",
//                getString(R.string.tile_label),
//                getString(R.string.service_active)
//            )

//            newIcon = Icon.createWithResource(
//                applicationContext,
//                ic_keyboard_black_24dp
//            )

            newState = Tile.STATE_ACTIVE

        } else {
//            newLabel = String.format(
//                Locale.US,
//                "%s %s",
//                getString(R.string.tile_label),
//                getString(R.string.service_inactive)
//            )

//            newIcon = Icon.createWithResource(
//                applicationContext,
//                ic_keyboard_hide_black_24dp
//            )

            newState = Tile.STATE_INACTIVE
        }

        // Change the UI of the tile.
//        tile.label = newLabel
//        tile.icon = newIcon
        tile.state = newState

        // Need to call updateTile for the tile to pick up changes.
        tile.updateTile()
    }

    private fun setAutoKeyboardBrightness(isActive:Boolean) {
        try{
//            val autoKeyboardKrightness = Settings.Global.getInt(applicationContext.contentResolver, "auto_keyboard_brightness")
//            Log.d(TAG, "#1 autoKeyboardKrightness = $autoKeyboardKrightness")

            if( isActive ) {
                Settings.Global.putInt(applicationContext.contentResolver, "auto_keyboard_brightness", 0)
            } else {
                Settings.Global.putInt(applicationContext.contentResolver, "auto_keyboard_brightness", 1)
            }

            Log.d(TAG, "isActive = $isActive")
        } catch (e:Settings.SettingNotFoundException) {
            e.printStackTrace()
        }
    }

    companion object {

        private val TAG = "QuickSettingsService"
        private val SERVICE_STATUS_FLAG = "serviceStatus"
        private val PREFERENCES_KEY = "com.google.android_quick_settings"
    }
}
