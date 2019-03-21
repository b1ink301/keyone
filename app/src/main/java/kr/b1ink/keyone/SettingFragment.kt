package kr.b1ink.keyone

import android.os.Bundle
import android.util.Log
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager

class SettingFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences_kbd, rootKey)

        val display_keyboard_auto_brightness = PreferenceManager.getDefaultSharedPreferences(context).getBoolean("display_keyboard_auto_brightness", false)
        val night_display_keyboard_brightness = PreferenceManager.getDefaultSharedPreferences(context).getInt("night_display_keyboard_brightness", 10)
        val night_display_navi_brightness = PreferenceManager.getDefaultSharedPreferences(context).getInt("night_display_navi_brightness", 10)

        Log.d("SettingFragment", "display_keyboard_auto_brightness=$display_keyboard_auto_brightness, night_display_keyboard_brightness=$night_display_keyboard_brightness, night_display_navi_brightness=$night_display_navi_brightness")
    }
}