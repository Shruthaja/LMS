package com.example.lms
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
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
                val bname_text = findViewById(R.id.editTextTextPersonName2) as EditText
                val bname=bname_text.text.toString()
                val cardno_text=findViewById(R.id.editTextTextPersonName4) as EditText
                val cardno=cardno_text.text.toString()
                val branchno=findViewById(R.id.editTextTextPersonName13) as EditText
                val bid=branchno.text.toString()
                val encodedBname = URLEncoder.encode(bname, "UTF-8")
                val encodedcardno=URLEncoder.encode(cardno,"UTF-8")
                val ebid=URLEncoder.encode(bid,"UTF-8")
                val urlString = "$baseUrl?bookname=$encodedBname&cardno=$encodedcardno&bid=$bid"
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
                println("response =====> "+ response.toString());
                val jsonResponse = (response.toString())

                val jsonData = response.toString();
                // Convert JSON string to JSONArray
                val jsonArray = JSONArray(jsonData)

                // Define table headers
                val headers = arrayOf("Book_id", "Branch_id", "No_of_copies")

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
                    tableBuilder.append(jsonObject.getString("book_id")).append(" | ")
                    tableBuilder.append(jsonObject.getString("branch_id")).append(" | ")
                    tableBuilder.append(jsonObject.getString("no_of_copies")).append(" | ")
                    tableBuilder.append("\n")
                }

                value.setText(tableBuilder)

            } catch (e: Exception) {
                e.printStackTrace()}

        }
        val btnclickme2 = findViewById(R.id.button2) as Button
        btnclickme2.setOnClickListener{
            val value=findViewById(R.id.textView6) as TextView
            try {
                // Create URL object


// Set the request body
                val baseUrl = "http://172.22.80.1/newbook.php"
                val newbname_text =findViewById(R.id.editTextTextPersonName3) as EditText
                val newbname=newbname_text.text.toString()
                val publisher_text =findViewById(R.id.editTextTextPersonName6) as EditText
                val publisher=publisher_text.text.toString()
                val encodednewbname = URLEncoder.encode(newbname, "UTF-8")
                val encodedpublisher=URLEncoder.encode(publisher,"UTF-8")
                val urlString = "$baseUrl?newerbookname=$encodednewbname&publisher=$encodedpublisher"
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

                val jsonData = response.toString();
                // Convert JSON string to JSONArray
                val jsonArray = JSONArray(jsonData)

                // Define table headers
                val headers = arrayOf("Book_id", "Branch_id", "No_of_copies")

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
                    tableBuilder.append(jsonObject.getString("book_id")).append(" | ")
                    tableBuilder.append(jsonObject.getString("branch_id")).append(" | ")
                    tableBuilder.append(jsonObject.getString("no_of_copies")).append(" | ")
                    tableBuilder.append("\n")
                }

                value.setText(tableBuilder)

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
                val sdate_text = findViewById(R.id.editTextDate2) as EditText
                val sdate=sdate_text.text.toString()
                val edate_text=findViewById(R.id.editTextDate) as EditText
                val edate=edate_text.text.toString()
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
                value.text = "";
//                val jsonResponse = (response.toString())
//                val rows = response.split("  ")
//                var i=0
//                for (row in rows) {
//                    value.append(row + System.lineSeparator())
//                }
                println("response -----> "+ response.toString())
                val jsonData = response.toString();
                // Convert JSON string to JSONArray
                val jsonArray = JSONArray(jsonData)

                // Define table headers
                val headers = arrayOf("Name", "Title", "branch_name", "Number of Days Delayed")

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
                    tableBuilder.append(jsonObject.getString("name")).append(" | ")
                    tableBuilder.append(jsonObject.getString("title")).append(" | ")
                    tableBuilder.append(jsonObject.getString("branch_name")).append(" | ")
                    tableBuilder.append(jsonObject.getString("Number_of_Days_delayed")).append(" | ")
                    tableBuilder.append("\n")
                }

                value.setText(tableBuilder)

                value.setMovementMethod(ScrollingMovementMethod())

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



