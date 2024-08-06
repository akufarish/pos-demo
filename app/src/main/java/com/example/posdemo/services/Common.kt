package com.example.posdemo.services

import java.text.NumberFormat
import java.util.Locale

object Common {

    const val API_URL = "https://392f-36-82-35-149.ngrok-free.app/"
     fun formatCurrency(nominal: Int): String {

        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(nominal)
    }
}