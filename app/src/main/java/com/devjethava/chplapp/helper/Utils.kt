package com.devjethava.chplapp.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.devjethava.chplapp.BuildConfig
import com.devjethava.chplapp.R
import org.json.JSONObject
import retrofit2.HttpException
import java.math.BigInteger
import java.security.MessageDigest
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date

object Utils {

    fun setupLoadingDialog(activity: Activity): AlertDialog {

        // Loading Dialog
        val builder = AlertDialog.Builder(activity)
        val dialogView = activity.layoutInflater.inflate(R.layout.progress_dialog, null)
        builder.setView(dialogView)
        builder.setCancelable(false)
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        return dialog
    }

    fun loadImage(context: Context, imgView: ImageView, url: String) {

        val requestOptions = RequestOptions()

        Glide.with(context).load(url).apply(requestOptions).into(imgView)
    }

    fun isConnectionAvailable(context: Context): Boolean {
        var result = false
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = connectivityManager.activeNetwork ?: return false
            val actNw =
                connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false
            result = when {
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
                else -> false
            }
        } else {
            connectivityManager.run {
                connectivityManager.activeNetworkInfo?.run {
                    result = when (type) {
                        ConnectivityManager.TYPE_WIFI -> true
                        ConnectivityManager.TYPE_MOBILE -> true
                        ConnectivityManager.TYPE_ETHERNET -> true
                        else -> false
                    }
                }
            }
        }

        return result
    }

    fun md5Hash(str: String): String {
        val md = MessageDigest.getInstance("MD5")
        val bigInt = BigInteger(1, md.digest(str.toByteArray(Charsets.UTF_8)))
        return String.format("%032x", bigInt)
    }

    @SuppressLint("SimpleDateFormat")
    fun convertDate(date: Date): String {
        val formatter: DateFormat = SimpleDateFormat("yyyy-MM-dd")
        return formatter.format(date)
    }

    fun parseErrorMessage(response: HttpException): String? {
        return try {
            val jsonObj = JSONObject(response.response()!!.errorBody()!!.charStream().readText())
            if (jsonObj.has("errors") && jsonObj.optJSONArray("errors").length() > 0) {
                return jsonObj.optJSONArray("errors").optJSONObject(0).optString("message")

            } else if (jsonObj.has("error")) {
                jsonObj.getString("error")
            } else {
                jsonObj.getString("message")
            }


        } catch (e: Exception) {
            null
        }
    }

    fun checkErrorCode(response: HttpException): Int {
        return try {
            response.response()!!.code()
        } catch (e: Exception) {
            400
        }
    }

    fun printLog(tag: String, msg: String) {
        if (BuildConfig.DEBUG) Log.e(tag, msg)
    }

    fun printLog(tag: String, msg: String, tr: Throwable) {
        if (BuildConfig.DEBUG) Log.e(tag, msg, tr)
    }
}