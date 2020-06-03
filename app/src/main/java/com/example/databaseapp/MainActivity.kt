package com.example.databaseapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        create an instance of a database
        val db : SQLiteDatabase = openOrCreateDatabase("appdb" , Context.MODE_PRIVATE,null)

        db.execSQL("CREATE TABLE IF NOT EXISTS jobs(jobtype VARCHAR, offeredby VARCHAR, phonenumber VARCHAR,requirements VARCHAR,estimatedpay VARCHAR,location VARCHAR, slotsavailabble VARCHAR)")

//        listen to a click event on the add data image
        btnPostJob.setOnClickListener {
            val jobtype : String = etJobtype.text.trim().toString()
            val offeredby : String = etOfferedBy.text.trim().toString()
            val phonenumber : String = etPhoneNumber.text.trim().toString()
            val requirements : String = etRequirements.text.trim().toString()
            val estimatedpay : String = etEstimatedPay.text.trim().toString()
            val location : String = etLocation.text.trim().toString()
            val slotsavailable : String = etSlotsAvailabile.text.trim().toString()

            if(jobtype.isEmpty() or offeredby.isEmpty() or phonenumber.isEmpty() or requirements.isEmpty() or estimatedpay.isEmpty() or location.isEmpty() or slotsavailable.isEmpty()){
//                show message to the user
                show_message("Missing Data" , "Please fill in all the fields given")
            }else{
//                store the data in the database
                db.execSQL("INSERT INTO jobs VALUES('"+jobtype+"','"+offeredby+"','"+phonenumber+"','"+requirements+"','"+estimatedpay+"','"+location+"','"+slotsavailable+"')")
                show_message("Success", "Job has been posted successfully")

//                clear the edit texts after successfully adding data into the database
                etJobtype.setText("")
                etOfferedBy.setText("")
                etPhoneNumber.setText("")
                etRequirements.setText("")
                etEstimatedPay.setText("")
                etLocation.setText("")
                etSlotsAvailabile.setText("")

            }
        }
        viewNext.setOnClickListener {
            val viewDatabaseData = Intent(this,UserActivity::class.java)
            startActivity(viewDatabaseData)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
         super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main,menu)

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
         var selectedOption = ""

        when(item?.itemId){

            R.id.aboutapp -> selectedOption = "About app"
            R.id.getHelp -> selectedOption = "Get help"
            R.id.testimonials -> selectedOption = "Testimonials"
        }


        Toast.makeText(this, "Option : " + selectedOption,Toast.LENGTH_SHORT).show()



        return super.onOptionsItemSelected(item)
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
