package com.example.databaseapp

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    @SuppressLint("Recycle")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

//        add a new user
        newUser.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

//        pull data from the database and get it displayed
//        open the database that was already created in the main activity
        val db : SQLiteDatabase = openOrCreateDatabase("appdb" , Context.MODE_PRIVATE,null)

//        pull data from the users table
        val sql = "SELECT * FROM jobs"

//        create user list[array]

        val jobs : ArrayList<DataItem> = ArrayList()

        val cursor = db.rawQuery(sql , null)

        if (cursor.count == 0){
              show_message("No Jobs", "Seems like there are available jobs now.")
            }else{
                while (cursor.moveToNext()) {
                    jobs.add(
                        DataItem(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4),
                            cursor.getString(5),
                            cursor.getString(6)
                        )
                    )
                    jobsList.adapter = CustomAdapter(this,jobs)
                }
        }



    }

    private fun show_message(title: String, message: String) {
        val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton("Ok", DialogInterface.OnClickListener({ dialog, which ->  dialog.dismiss()}))
        alertDialog.create().show()
    }
}
