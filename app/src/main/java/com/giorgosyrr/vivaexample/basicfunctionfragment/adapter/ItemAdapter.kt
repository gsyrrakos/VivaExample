package com.giorgosyrr.vivaexample.basicfunctionfragment.adapter


import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.giorgosyrr.vivaexample.MainActivity
import com.giorgosyrr.vivaexample.data.model.DataFromApi
import com.giorgosyrr.vivaexample.R
import com.giorgosyrr.vivaexample.basicfunctionfragment.detailsfragment.DetailsFragment
import kotlin.collections.ArrayList

class ItemAdapter(private val mList: List<DataFromApi>) :
    RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view: View =
            LayoutInflater.from(parent.context).inflate(R.layout.each_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = mList[position]

      holder.mTextView.text = model.Name
        Glide.with(holder.iconPreview).load(model.Thumbnail).into(holder.iconPreview)
        Log.d("url,",model.Thumbnail.toString())
        holder.mTextViewPrice.text=model.Price

        renderNestedList(holder, model)
    }


    private fun renderNestedList(holder: ItemViewHolder, model: DataFromApi) {

        holder.linearLayout.setOnClickListener {
            val myActivity = holder.itemView.context as MainActivity
            val myFragment = DetailsFragment.newInstance(
                model.Name ?:"",
                model.Image ?:"",
                model.Description ?:"",
                model.Price ?:"")

            myActivity.supportFragmentManager.beginTransaction()
                .replace(R.id.myContainer, myFragment).addToBackStack("my").commit()
        }


    }

    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val linearLayout: LinearLayout
        val mTextView: TextView
        val mTextViewPrice: TextView
        val iconPreview: ImageView

        init {
            linearLayout = itemView.findViewById(R.id.linear_layout)
            mTextView = itemView.findViewById(R.id.textViewName)
            iconPreview = itemView.findViewById(R.id.image_icon)
            mTextViewPrice = itemView.findViewById(R.id.textViewPrice)
        }
    }
}