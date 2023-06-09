package com.example.lms

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class page2 : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_page2)
        val switchToSecondActivity1= findViewById<Button>(R.id.button5) as Button
        switchToSecondActivity1.setOnClickListener({
            switchActivities()
        })
        val switchToSecondActivity3= findViewById<Button>(R.id.button10) as Button
        switchToSecondActivity3.setOnClickListener({
            switchActivities3()
        })



        val btnclickme = findViewById(R.id.button6) as Button
        // set on-click listener
        btnclickme.setOnClickListener {
            System.out.println("hello!");
            val borrowname_text= findViewById<EditText>(R.id.editTextTextPersonName)
            val borroweraddress_text= findViewById<EditText>(R.id.editTextTextPersonName5)
            val borrowerphone_text= findViewById<EditText>(R.id.editTextTextPersonName7)
            val baseUrl = "http://172.22.80.1/newborrower.php"

            val eborrowname = URLEncoder.encode(borrowname_text.text.toString(), "UTF-8")
            val eborroweraddress= URLEncoder.encode(borroweraddress_text.text.toString(),"UTF-8")
            val eborrowerphon= URLEncoder.encode(borrowerphone_text.text.toString(),"UTF-8")

            val urlString = "$baseUrl?borrowername=$eborrowname&borroweradd=$eborroweraddress&borrowerphone=$eborrowerphon"
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

            val value= findViewById<TextView>(R.id.textView7)
            val jsonResponse = (response.toString())

            val jsonData = response.toString();
            // Convert JSON string to JSONArray
            val jsonArray = JSONArray(jsonData)

            // Define table headers
            val headers = arrayOf("Card_no", "Name", "Address", "Phone")

            // Create table string builder
            val tableBuilder = StringBuilder()

            // Append table headers
            tableBuilder.append("| ")
            headers.forEach { header ->
                tableBuilder.append(header).append(" | ")
            }
            tableBuilder.append("\n")

            // Append separator row
            tableBuilder.append("| ")
            headers.forEach {
                tableBuilder.append("-".repeat(it.length)).append(" | ")
            }
            tableBuilder.append("\n")

            // Append table rows
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                tableBuilder.append("| ")
                tableBuilder.append(jsonObject.getString("card_no")).append(" | ")
                tableBuilder.append(jsonObject.getString("name")).append(" | ")
                tableBuilder.append(jsonObject.getString("address")).append(" | ")
                tableBuilder.append(jsonObject.getString("phone")).append(" | ")
                tableBuilder.append("\n")
            }

            value.text = tableBuilder
        }
        val btnclickme2=findViewById(R.id.button7) as Button
        btnclickme2.setOnClickListener{
            val boockname=findViewById(R.id.editTextTextPersonName8) as EditText
            val eboockname = URLEncoder.encode(boockname.text.toString(), "UTF-8")
            val baseUrl = "http://172.22.80.1/bookcopy.php"

            val urlString = "$baseUrl?bookname=$eboockname"
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

            val value= findViewById(R.id.textView7) as TextView
            val jsonResponse = (response.toString())

            val jsonData = response.toString();
            // Convert JSON string to JSONArray
            val jsonArray = JSONArray(jsonData)

            // Define table headers
            val headers = arrayOf("Branch_id", "Branch_name", "Copies_Loaned")

            // Create table string builder
            val tableBuilder = StringBuilder()

            // Append table headers
            tableBuilder.append("| ")
            headers.forEach { header ->
                tableBuilder.append(header).append(" | ")
            }
            tableBuilder.append("\n")

            // Append separator row
            tableBuilder.append("| ")
            headers.forEach {
                tableBuilder.append("-".repeat(it.length)).append(" | ")
            }
            tableBuilder.append("\n")

            // Append table rows
            for (i in 0 until jsonArray.length()) {
                val jsonObject: JSONObject = jsonArray.getJSONObject(i)
                tableBuilder.append("| ")
                tableBuilder.append(jsonObject.getString("branch_id")).append(" | ")
                tableBuilder.append(jsonObject.getString("branch_name")).append(" | ")
                tableBuilder.append(jsonObject.getString("copies_loaned")).append(" | ")
                tableBuilder.append("\n")
            }

            value.text = tableBuilder
        }

    }

    private fun switchActivities() {
        val switchActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(switchActivityIntent)

    }
    private fun switchActivities3() {
        val switchActivityIntent = Intent(this, sixthquery::class.java)
        startActivity(switchActivityIntent)

    }


}