package com.tommaso.app4store

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.IOException

class MainActivity : AppCompatActivity() {


    public val EMOTION_KEY: String = "EMOTION_KEY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title="Write something"
        var textToFind: String
        var testo = findViewById(R.id.testo) as TextInputEditText
        var textButton = findViewById(R.id.textButton) as Button
        textButton.setOnClickListener {
            textToFind = testo.text.toString()
            Toast.makeText(this, textToFind, Toast.LENGTH_LONG).show()
            analizeText(textToFind)
        }

    }

    fun analizeText(text: String) {
        goToResult(
            "   {\"emotions_detected\":[\"joy\"],\n" +
                    "         \"emotion_scores\":{\"joy\":0.1345922573875,\"sadness\":0.022857406778851,\"surprise\":0.0087088390785547,\"disgust\":0,\"anger\":0,\"fear\":0},\n" +
                    "         \"version\":\"7.0.0\",\n" +
                    "         \"author\":\"twinword inc.\",\n" +
                    "         \"email\":\"help@twinword.com\",\n" +
                    "         \"result_code\":\"200\",\n" +
                    "         \"result_msg\":\"Success\"}"
        )
        return
        val client = OkHttpClient()
        val mediaType: MediaType? = "application/x-www-form-urlencoded".toMediaTypeOrNull()
        val body = RequestBody.create(
            mediaType,
            "text=After%20living%20abroad%20for%20such%20a%20long%20time%2C%20seeing%20my%20family%20was%20the%20best%20present%20I%20could%20have%20ever%20wished%20for."
        )
        val request = Request.Builder()
            .url("https://twinword-emotion-analysis-v1.p.rapidapi.com/analyze/")
            .post(body)
//            .addHeader("x-rapidapi-host", "twinword-emotion-analysis-v1.p.rapidapi.com")
//            .addHeader("x-rapidapi-key", "053de9ea63msh610c0fe50a7e40fp1c89f0jsn6d16b9752791")
//            .addHeader("x-rapidapi-subscription", "FREE")
//            .addHeader("x-rapidapi-user", "tommo.ago")
//            .addHeader("Key", "f0796c1ffcmsh677fa61536b75bfp1ea37bjsn537abc1b3fd6")
//            .addHeader("content-type", "application/x-www-form-urlencoded")
            .addHeader("x-rapidapi-host", "twinword-emotion-analysis-v1.p.rapidapi.com")
            .addHeader("x-rapidapi-key", "053de9ea63msh610c0fe50a7e40fp1c89f0jsn6d16b9752791")
            .addHeader("content-type", "application/x-www-form-urlencoded")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(
                call: Call,
                e: IOException
            ) {
                Log.d("asda", "errore: ", e)
            }

            @Throws(IOException::class)
            override fun onResponse(
                call: Call,
                response: Response
            ) {
                var stringResponse = response.body?.string()
                Log.d("asda", "andata a buon fine " + stringResponse)
                runOnUiThread {
                    Toast.makeText(applicationContext, stringResponse, Toast.LENGTH_LONG)
                        .show()
                    goToResult(stringResponse)
                }

            }
        })
    }

    fun goToResult(data: String?) {
        val intent = Intent(this, ResultActivity::class.java)
        var extras = Bundle()
        extras.putString(EMOTION_KEY, data)
        intent.putExtras(extras)
        startActivity(intent)
    }
}

