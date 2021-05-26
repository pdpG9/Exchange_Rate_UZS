package com.example.exchange_rate_uzs.until

import com.google.gson.Gson

inline fun <reified T> String.getDataFromString(): T {
    val gson = Gson()
    return gson.fromJson(this, T::class.java)
}
fun getPrecent(rate: String, diff: String): String {
    val n: Double = rate.toDouble()
    val p: Double = diff.toDouble()
    val k = n - p
    val f =( (p / k) * 100).toString()
    if (f.length>=5){
        if (!f.startsWith("-")){
            return f.substring(0,4)
        }
      return  f.substring(0,5)
    }
    return f
}