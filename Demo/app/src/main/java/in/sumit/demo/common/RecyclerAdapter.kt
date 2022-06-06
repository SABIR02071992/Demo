package `in`.sumit.demo.common

import `in`.sumit.demo.R
import `in`.sumit.demo.model.Category
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import java.util.ArrayList
class RecyclerAdapter() : RecyclerView.Adapter<RecyclerAdapter.MyHolder>() {
    var context: Context?=null
    var res = 0
    var arrayList: ArrayList<Category> = ArrayList<Category>()
    //    var onClickInterface: onClickInterface? = null
    constructor(context: Context, res: Int, arrayList: ArrayList<Category>) : this() {
        this.context = context
        this.res = res
        this.arrayList = arrayList
//        onClickInterface = context as AddBank
//        this.onClickInterface=onClickInterface

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerAdapter.MyHolder {
        val v = LayoutInflater.from(context).inflate(res, parent, false)
        return MyHolder(v)
    }
    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        val m: Category = arrayList[position]


        holder.tv?.text=m.name
        Glide.with(context!!).load(m.icon).centerCrop().into(holder.img!!)


    }


    override fun getItemCount(): Int {
        return arrayList.size
    }

    inner class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var img:ImageView?=null
        var tv:TextView?=null
        init {
            img=itemView.findViewById(R.id.set_img)
            tv=itemView.findViewById(R.id.set_text)


        }
    }


}

