package me.i32xlevel.dzonetask.ui

import android.content.Context
import androidx.appcompat.app.AlertDialog
import me.i32xlevel.dzonetask.R

object ProgressDialog {
    private var dialog: AlertDialog? = null

    fun show(context: Context) {
        dialog?.show() ?: run {
            dialog = AlertDialog.Builder(context)
                .setCancelable(false)
                .setView(R.layout.progress_dialog)
                .create()
            dialog?.show()
        }
    }

    fun dismiss() {
        dialog?.dismiss()
    }
}