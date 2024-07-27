package com.example.posdemo.services

import java.text.NumberFormat
import java.util.Locale

object Common {
     fun formatCurrency(nominal: Int): String {

        return NumberFormat.getCurrencyInstance(Locale("id", "ID")).format(nominal)
    }
}