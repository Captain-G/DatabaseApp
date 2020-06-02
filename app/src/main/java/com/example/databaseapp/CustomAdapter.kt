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
        var jobtype:TextView
        var offeredby:TextView
        var phonenumber:TextView
        var requirements:TextView
        var estimatedpay:TextView
        var location:TextView
        var slotsavailable:TextView

        init {

            this.jobtype = row?.findViewById(R.id.tvJobtype) as TextView
            this.offeredby = row?.findViewById(R.id.tvOfferedby) as TextView
            this.phonenumber = row?.findViewById(R.id.tvPhonenumber) as TextView
            this.requirements = row?.findViewById(R.id.tvRequirements) as TextView
            this.estimatedpay = row?.findViewById(R.id.tvEstimatedpay) as TextView
            this.location = row?.findViewById(R.id.tvLocation) as TextView
            this.slotsavailable = row?.findViewById(R.id.tvSlotsavailable) as TextView

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
        viewHolder.jobtype.text = DataItem.jobtype
        viewHolder.offeredby.text = DataItem.offeredby
        viewHolder.phonenumber.text = DataItem.phonenumber
        viewHolder.requirements.text = DataItem.requirements
        viewHolder.estimatedpay.text = DataItem.estimatedpay
        viewHolder.location.text = DataItem.location
        viewHolder.slotsavailable.text = DataItem.slotsavailable


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