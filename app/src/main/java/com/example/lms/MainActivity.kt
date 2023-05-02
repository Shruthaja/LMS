package com.example.lms
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import org.json.*
import org.json.JSONTokener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        // get reference to button
        val btnclickme = findViewById(R.id.button3) as Button
        // set on-click listener
        btnclickme.setOnClickListener {
            val value=findViewById(R.id.textView6) as TextView
            try {
                // Create URL object
                val url = URL("http://172.22.80.1/hello.php/resource")

                // Create HTTP connection
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"

                // Read response
                val inStream = BufferedReader(InputStreamReader(connection.inputStream))
                val response = StringBuilder()
                var inputLine: String?
                while (inStream.readLine().also { inputLine = it } != null) {
                    response.append(inputLine)
                }
                inStream.close()
                val jsonObject = JSONTokener(response.toString()).nextValue() as JSONObject
                val jsonArray = jsonObject.getJSONArray("data")
                val tableBuilder = StringBuilder()

                // Print response
//                value.setText(response.toString())
                for (i in 0 until jsonArray.length()) {
                    val item = jsonArray.getJSONObject(i)

                }
                value.setText(jsonObject.getJSONObject("title").toString())

            } catch (e: Exception) {
                e.printStackTrace()}
//            value.setText("Result")

        }

        val switchToSecondActivity=findViewById(R.id.button4) as Button
        switchToSecondActivity.setOnClickListener({
            switchActivities()
        })



}
    private fun switchActivities() {
        val switchActivityIntent = Intent(this, page2::class.java)
        startActivity(switchActivityIntent)
    }}



