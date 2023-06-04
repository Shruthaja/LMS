package com.example.lms

import android.content.Intent
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import org.json.JSONObject
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import org.json.JSONArray

data class BookList (
    @SerializedName("result") var result: Array<BookClass>? = null
)


data class BookClass (

    @SerializedName("book_id"        ) var bookId         : String? = null,
    @SerializedName("title"          ) var title          : String? = null,
    @SerializedName("LateFeeBalance" ) var LateFeeBalance : String? = null

)

class sixthquery : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixthquery)

        val switchToSecondActivity=findViewById(R.id.button9) as Button
        switchToSecondActivity.setOnClickListener({
            switchActivities()
        })
        val switchToSecondActivity2=findViewById(R.id.button8) as Button
        switchToSecondActivity2.setOnClickListener({
            switchActivities2()
        })

        val btnclickme11=findViewById(R.id.button11) as Button

        val btnclickme12 = findViewById(R.id.button12) as Button

        btnclickme11.setOnClickListener {

            val bName= findViewById(R.id.editTextTextPersonName9) as EditText
            val cardNo= findViewById(R.id.editTextTextPersonName10) as EditText
            val ebname=URLEncoder.encode(bName.text.toString(), "UTF-8")
            val ecardno=URLEncoder.encode(cardNo.text.toString(), "UTF-8")

            val urlVal = "http://172.22.80.1/bookCopySixth.php";
            val urlString = "$urlVal?borrName=$ebname&borrNum=$ecardno"
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

            val value= findViewById(R.id.textView9) as TextView
//            val jsonResponse = (response.toString())

            val jsonData = response.toString();
            // Convert JSON string to JSONArray
            val jsonArray = JSONArray(jsonData)

            // Define table headers
            val headers = arrayOf("Card_no", "Name", "Late Fee Balance")

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
                tableBuilder.append(jsonObject.getString("LateFeeBalance")).append(" | ")
                tableBuilder.append("\n")
            }


            value.text=tableBuilder
            value.setMovementMethod(ScrollingMovementMethod())

//            value.text = jsonResponse
//            value.setMovementMethod(ScrollingMovementMethod())


        }

        btnclickme12.setOnClickListener{

            val bookName= findViewById(R.id.editTextTextPersonName11) as EditText
            val bookId= findViewById(R.id.editTextTextPersonName12) as EditText
            val ebname=URLEncoder.encode(bookName.text.toString(), "UTF-8")
            val ecardno=URLEncoder.encode(bookId.text.toString(), "UTF-8")

            val urlVal = "http://172.22.80.1/bookCopySixthB.php";
            val urlString = "$urlVal?bookName=$ebname&bookNum=$ecardno"
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

            val value= findViewById(R.id.textView9) as TextView
//            val jsonResponse = "{result:"+(response.toString())+"}"
//            val gson = GsonBuilder().setPrettyPrinting().create()
//            val company = Gson().fromJson(jsonResponse, BookList::class.java)
//            val prettyJsonString = gson.toJson(company)
//
//            value.text=prettyJsonString
//
//            System.out.println(response.toString())
            val jsonData = response.toString();
            // Convert JSON string to JSONArray
            val jsonArray = JSONArray(jsonData)

            // Define table headers
            val headers = arrayOf("Book ID", "Title", "Late Fee Balance")

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
                tableBuilder.append(jsonObject.getString("title")).append(" | ")
                tableBuilder.append(jsonObject.getString("LateFeeBalance")).append(" | ")
                tableBuilder.append("\n")
            }


            value.text=tableBuilder
            value.setMovementMethod(ScrollingMovementMethod())

        }




    }

    private fun switchActivities() {
        val switchActivityIntent = Intent(this, MainActivity::class.java)
        startActivity(switchActivityIntent)
    }
    private fun switchActivities2() {
        val switchActivityIntent = Intent(this, page2::class.java)
        startActivity(switchActivityIntent)
    }
}