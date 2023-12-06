package com.example.karaoke

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.appcompat.app.AlertDialog

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity()
{

    override fun onCreate(savedInstanceState: Bundle?) {

        // boilerplate stuff
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        title = "Karaoke"

        // homescreen choices
        val arrayAdapter: ArrayAdapter<*>
        val choices = arrayOf(
            "Downloaded Lyrics",
            "Top 100 Charts",
            "Search by Artist",
            "Search by Song",
        )

        // access the listView from xml file
        val mListView = findViewById<ListView>(R.id.userlist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, choices)
        mListView.adapter = arrayAdapter

        // when an item in the list is clicked
        mListView.setOnItemClickListener { parent, view, position, id ->

            // create a new screen displaying the selected songs
            val intent = Intent(this, SongSelector::class.java)

            // store the category
            intent.putExtra("category", id)
            intent.putExtra("header", choices[id.toInt()])

            /*
                0: downloaded lyrics
                1. top 100 charts
                2. search by artist
                3. search by song
             */

            // if category has search bar
            if (id == 2.toLong() || id == 3.toLong()) {
                val input = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Enter Text")
                    .setView(input)
                    .setPositiveButton("OK", DialogInterface.OnClickListener { dialog, which ->
                        val enteredText = input.text.toString()
                        intent.putExtra("query", enteredText)
                        startActivity(intent)
                    })
                    .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialog, which ->
                        // Handle cancel
                    })
                    .create()

                dialog.show()
            }

            // if category does not
            else
            {
                startActivity(intent)
            }
        }
    }
}