package io.github.domi04151309.powerapp

import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast

class PowerOptions(private val context: Context) {

    private fun shell(command: String) {
        try {
            val p = Runtime.getRuntime()
                    .exec(arrayOf("su", "-c", command))
            p.waitFor()
        } catch (e: Exception) {
            try {
                AlertDialog.Builder(context)
                        .setTitle(R.string.action_failed)
                        .setMessage(R.string.action_failed_summary)
                        .setPositiveButton(android.R.string.ok) { _, _ -> }
                        .show()
            } catch (e: Exception) {
                Toast.makeText(context,R.string.action_failed_summary,Toast.LENGTH_LONG).show()
            }
            Log.e("Superuser", e.toString())
        }

    }

    fun shutdown() {
        shell("reboot -p")
    }

    fun reboot() {
        shell("reboot")
    }

    fun rebootIntoRecovery() {
        shell("reboot recovery")
    }

    fun rebootIntoBootloader() {
        shell("reboot bootloader")
    }

    fun softReboot() {
        shell("setprop ctl.restart zygote")
    }

    fun restartSystemUI() {
        shell("killall com.android.systemui")
    }

    fun turnOffScreen() {
        shell("input keyevent KEYCODE_POWER")
    }
}