package com.example.pos_demo_mvvm.utils

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import com.example.pos_demo_mvvm.utils.di.Retrofit
import com.example.pos_demo_mvvm.utils.token.TokenManager
import java.text.NumberFormat
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun setToken(token: String, context: Context) {
    val retrofit = Retrofit()
    retrofit.setToken(token)
    val tokenManager = TokenManager(context)
    tokenManager.saveToken(token)
}

fun formatCurrency(nominal: Int): String {

    return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(nominal)
}

@RequiresApi(Build.VERSION_CODES.O)
fun dateFormat(date: String): String {
    val zonedDateTime = ZonedDateTime.parse(date)
    val localDateTime = zonedDateTime.withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime()
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val formattedDateTime = localDateTime.format(formatter)

    return formattedDateTime
}