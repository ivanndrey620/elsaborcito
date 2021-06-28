package com.androidapp.elsaborcito.extension

import android.media.RingtoneManager
import android.net.Uri
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.androidapp.elsaborcito.R

/**
 *  Created by Iván Bolaños on 23/06/2021
 */
fun AppCompatActivity.showAlert(title: String, message: String, onCancelClick: (() -> Unit)? = null, onOkClick: (() -> Unit)? = null) {

    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(applicationContext, notification)

    r.play()

    AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle).apply {
        setTitle(title)
        setMessage(message)
        setPositiveButton("Aceptar") { _, _ ->
            onOkClick?.invoke()
        }
        setNegativeButton("Cancelar") { _, _ ->
            onCancelClick?.invoke()
        }
        show()
    }
}

fun Fragment.showSingleChoiceAlert(title: String, message: String, items: Array<String>, onCancelClick: (() -> Unit)? = null, onOkClick: ((code: String) -> Unit)? = null) {
    val dialogContext = context
    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(context, notification)
    r.play()
    if (isAdded && isVisible && activity?.isFinishing == false && dialogContext != null) {
        var interval = 0
        AlertDialog.Builder(dialogContext, R.style.AppCompatAlertDialogStyle).apply {
            setTitle(title)
            setSingleChoiceItems(items, -1) { _, i ->
                interval = i
            }
            setNegativeButton("Cancelar") { _, _ ->
                onCancelClick?.invoke()
            }
            setPositiveButton("Aceptar") { dialog, _ ->
                onOkClick?.invoke(items[interval])
                dialog.dismiss()
            }
            show()
        }
    }
}



fun Fragment.showAlert(title: String, message: String, onCancelClick: (() -> Unit)? = null, onOkClick: (() -> Unit)? = null) {
    val dialogContext = context

    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(context, notification)
    r.play()

    if (isAdded && isVisible && activity?.isFinishing == false && dialogContext != null) {
        AlertDialog.Builder(dialogContext, R.style.AppCompatAlertDialogStyle).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Aceptar") { _, _ ->
                onOkClick?.invoke()
            }
            setNegativeButton("Cancelar") { _, _ ->
                onCancelClick?.invoke()
            }
            show()
        }
    }
}

fun Fragment.showOkAlertCancelable(title: String, message: String, cancelable: Boolean, onOkClick: (() -> Unit)? = null) {
    val dialogContext = context

    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(context, notification)
    r.play()

    if (isAdded && isVisible && activity?.isFinishing == false && dialogContext != null) {
        AlertDialog.Builder(dialogContext, R.style.AppCompatAlertDialogStyle).apply {
            setCancelable(cancelable)
            setTitle(title)
            setMessage(message)
            setPositiveButton("Aceptar") { _, _ ->
                onOkClick?.invoke()
            }
            show()
        }
    }
}

fun Fragment.showOkAlert(title: String, message: String, onOkClick: (() -> Unit)? = null) {
    val dialogContext = context

    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(context, notification)
    r.play()

    if (isAdded && isVisible && activity?.isFinishing == false && dialogContext != null) {
        AlertDialog.Builder(dialogContext, R.style.AppCompatAlertDialogStyle).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Aceptar") { _, _ ->
                onOkClick?.invoke()
            }
            show()
        }
    }
}

fun Fragment.showOkAlert(@StringRes title: Int, @StringRes message: Int, onOkClick: (() -> Unit)? = null) {
    val dialogContext = context

    val notification: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
    val r = RingtoneManager.getRingtone(context, notification)
    r.play()

    if (isAdded && isVisible && activity?.isFinishing == false && dialogContext != null) {
        AlertDialog.Builder(dialogContext, R.style.AppCompatAlertDialogStyle).apply {
            setTitle(title)
            setMessage(message)
            setPositiveButton("Aceptar") { _, _ ->
                onOkClick?.invoke()
            }
            show()
        }
    }
}

