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
import java.net.URLEncoder
import org.json.JSONTokener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)
        // get reference to button
        val btnclickme = findViewById(R.id.button) as Button
        // set on-click listener
        btnclickme.setOnClickListener {
            val value=findViewById(R.id.textView6) as TextView
            try {
                // Create URL object


// Set the request body
                val baseUrl = "http://172.22.80.1/hello.php"
                val bname = "To Kill a Mockingbird"
                val cardno="123456"
                val encodedBname = URLEncoder.encode(bname, "UTF-8")
val encodedcardno=URLEncoder.encode(cardno,"UTF-8")
                val urlString = "$baseUrl?bookname=$encodedBname&cardno=$encodedBname"
System.out.println(urlString);
                val url = URL(urlString)

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

                val jsonResponse = (response.toString())

                value.setText(jsonResponse)

            } catch (e: Exception) {
                e.printStackTrace()}
//            value.setText("Result")

        }
        val btnclickme2 = findViewById(R.id.button2) as Button
        btnclickme2.setOnClickListener{
            val value=findViewById(R.id.textView6) as TextView
            try {
                // Create URL object


// Set the request body
                val baseUrl = "http://172.22.80.1/newbook.php"
                val newbname = "To Kill a Mockingbird"
                val publisher="ABC"
                val encodednewbname = URLEncoder.encode(newbname, "UTF-8")
                val encodedpublisher=URLEncoder.encode(publisher,"UTF-8")
                val urlString = "$baseUrl?bookname=$encodednewbname&publisher=$encodedpublisher"
                System.out.println(urlString);
                val url = URL(urlString)

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

                val jsonResponse = (response.toString())

                value.setText(jsonResponse+"New value!")

            } catch (e: Exception) {
                e.printStackTrace()}

        }
        val btnclickme3=findViewById(R.id.button3) as Button
        btnclickme3.setOnClickListener{
            val value=findViewById(R.id.textView6) as TextView
            try {
                // Create URL object


// Set the request body
                val baseUrl = "http://172.22.80.1/checkdue.php"
                val sdate = "abc"
                val edate="ABC"
                val encodedsdate = URLEncoder.encode(sdate, "UTF-8")
                val encodededate=URLEncoder.encode(edate,"UTF-8")
                val urlString = "$baseUrl?sdate=$encodedsdate&edate=$encodededate"
                System.out.println(urlString);
                val url = URL(urlString)

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

                val jsonResponse = (response.toString())

                value.setText(jsonResponse+"Date value!")

            } catch (e: Exception) {
                e.printStackTrace()}

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



