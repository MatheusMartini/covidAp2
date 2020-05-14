package com.example.ap2_covid19.API

import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.example.ap2_covid19.classes.Countries
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

object HTTP_countries{
    val urlCountries = "https://covid19-brazil-api.now.sh/api/report/v1/countries"

    fun hasConnection(ctx: Context): Boolean{
        val cm = ctx.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager
        val info =  cm.activeNetworkInfo
        return info != null && info.isConnected
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun loadCountries(): List<Countries>?{
        val country = OkHttpClient.Builder()
            .readTimeout(5, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .build()

        val request = Request.Builder()
            .url(urlCountries)
            .build()

        val response = country.newCall(request).execute()
        val jsonString = response.body?.string()

        val jsonObject = JSONObject(jsonString)
        val jsonArray = jsonObject.getJSONArray("data")

        return readCountries(jsonArray)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun readCountries(json: JSONArray) : List<Countries>?{
        val paises = arrayListOf<Countries>()
        try {
            for (i in 0 until json.length()) {
                val js = json.getJSONObject(i)

                val date = dateFormatter(js.getString("updated_at").substring(0,10))

                val country = Countries(
                    js.getString("country"),
                    js.getInt("cases"),
                    js.getInt("confirmed"),
                    js.getInt("deaths"),
                    js.getInt("recovered"),
                    date
                )
                paises.add(country)
            }
        }catch (e: IOException) {
            Log.e("Error", "Impossible do read JSON")
        }
        return paises
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun dateFormatter(data: String): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        val date = LocalDate.parse(data)
        val formattedDate = date.format(formatter)
        return formattedDate
    }
}