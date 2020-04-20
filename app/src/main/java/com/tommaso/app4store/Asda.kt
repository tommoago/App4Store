package com.tommaso.app4store

import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class Asda {
    fun doSomething() {
        val client = OkHttpClient()
        val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = RequestBody.create(
            mediaType,
            "text=After%20living%20abroad%20for%20such%20a%20long%20time%2C%20seeing%20my%20family%20was%20the%20best%20present%20I%20could%20have%20ever%20wished%20for."
        )
        val request = Request.Builder()
            .url("https://twinword-emotion-analysis-v1.p.rapidapi.com/analyze/")
            .post(body)
            .addHeader("x-rapidapi-host", "twinword-emotion-analysis-v1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "053de9ea63msh610c0fe50a7e40fp1c89f0jsn6d16b9752791")
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(
                call: Call,
                e: IOException
            ) {
            }

            @Throws(IOException::class)
            override fun onResponse(
                call: Call,
                response: Response
            ) {
            }
        })
        val response = client.newCall(request).execute()
    }
}