package com.example.databaseapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

//        add a new user

        adduser.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

//        pull data from the database and get it displayed
//        open the database that was already created in the main activity
        val db : SQLiteDatabase = openOrCreateDatabase("appdb" , Context.MODE_PRIVATE,null)

//        pull data from the users table
        val sql = "SELECT * FROM users"

//        create user list[array]

        val users : ArrayList<DataItem> = ArrayList()

        val cursor = db.rawQuery(sql , null)

        if (cursor.count == 0){
              show_message("No Users", "Seems like there are no users in the database")
            }else{
                while (cursor.moveToNext()) {
                    users.add(
                        DataItem(
                            cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3)
                        )
                    )
                    userList.adapter = CustomAdapter(this,users)

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
