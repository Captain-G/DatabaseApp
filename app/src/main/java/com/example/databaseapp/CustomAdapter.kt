package com.example.databaseapp


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
//import androidx.recyclerview.widget.RecyclerView
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.openOrCreateDatabase
import android.util.Log



class CustomAdapter(var context: Context, var data: ArrayList<DataItem>): BaseAdapter() {


    private class ViewHolder(row:View?){
        var name:TextView
        var profession:TextView
        var residence:TextView
        var password:TextView
        var imgdelete:ImageView

        init {

            this.name = row?.findViewById(R.id.tvName) as TextView
            this.profession = row?.findViewById(R.id.tvProfession) as TextView
            this.residence = row?.findViewById(R.id.tvresidence) as TextView
            this.password = row?.findViewById(R.id.tvpassword) as TextView
            this.imgdelete = row?.findViewById(R.id.imgDelete) as ImageView

        }
    }



    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view:View?
        val viewHolder:ViewHolder
        if (convertView == null){
            val layout = LayoutInflater.from(context)
            view = layout.inflate(R.layout.card_row,parent,false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val DataItem:DataItem = getItem(position) as DataItem
        viewHolder.name.text = DataItem.name
        viewHolder.profession.text = DataItem.profession
        viewHolder.residence.text = DataItem.residence
        viewHolder.password.text = DataItem.password


        return view as View
    }

    override fun getItem(position: Int): Any {
        return  data.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return data.count()
    }

    fun  getIntID(position: Long):Int{
        return position.toInt()
    }


}