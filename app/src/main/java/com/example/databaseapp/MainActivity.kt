package com.example.databaseapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        create an instance of a database
        val db : SQLiteDatabase = openOrCreateDatabase("appdb" , Context.MODE_PRIVATE,null)

        db.execSQL("CREATE TABLE IF NOT EXISTS users(name VARCHAR, profession VARCHAR, residence VARCHAR, password VARCHAR)")

//        listen to a click event on the add data image
        addUser.setOnClickListener {
            val name : String = etName.text.trim().toString()
            val profession : String = etProfession.text.trim().toString()
            val residence : String = etResidence.text.trim().toString()
            val password : String = etPassword.text.trim().toString()

            if(name.isEmpty() or profession.isEmpty() or residence.isEmpty() or password.isEmpty()){
//                show message to the user
                show_message("Missing Data" , "Please fill in all the fields")
            }else{
//                store the data in the database
                db.execSQL("INSERT INTO users VALUES('"+name+"','"+profession+"','"+residence+"','"+password+"')")
                show_message("Success", "Data has been entered successfully")

//                clear the edit texts after successfully adding data into the database
                etName.setText("")
                etProfession.setText("")
                etResidence.setText("")
                etPassword.setText("")

            }
        }
        viewNext.setOnClickListener {
            val viewDatabaseData = Intent(this,UserActivity::class.java)
            startActivity(viewDatabaseData)
        }
    }

    private fun show_message(title: String, message: String) {
        val alertDialog : AlertDialog.Builder = AlertDialog.Builder(this)
        alertDialog.setCancelable(false)
        alertDialog.setTitle(title)
        alertDialog.setMessage(message)

        alertDialog.setPositiveButton("Ok",DialogInterface.OnClickListener({dialog, which ->  dialog.dismiss()}))
        alertDialog.create().show()
    }
}
