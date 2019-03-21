package kr.b1ink.keyone

import android.Manifest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val permissionCheck = ContextCompat.checkSelfPermission(this,
            Manifest.permission.WRITE_SECURE_SETTINGS
        )

        if( permissionCheck==PackageManager.PERMISSION_GRANTED ) {

            text.text = getString(R.string.ok)
//            val builder = AlertDialog.Builder(this)
//            builder.setMessage(R.string.adb_grant)
//                .setPositiveButton(R.string.ok) { _, _ ->
////                    finish()
//                }
//            builder.create().show()

//            val settingFragment = SettingFragment()
//            supportFragmentManager.beginTransaction().replace(R.id.content_frame, settingFragment).commit()
        }
    }
}
