package com.example.movieplace.ui.scenes

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.model.Scene
import com.google.gson.GsonBuilder

class MyScenesRecyclesViewAdapter(private val context: Context?) : RecyclerView.Adapter<MyScenesRecyclesViewAdapter.ViewHolder>() {

    /**
     * Onclick to item. Updated when activitiesList developed
     */
    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as Scene
        val intent = Intent(context, ScenesDesc::class.java)
        intent.putExtra("scene", GsonBuilder().create().toJson(item))
        context?.startActivity(intent)
    }
    private var scenes: List<Scene> = ArrayList()

    /**
     * it inflates the view of each scene and sees the ViewHolder of the view
     * @param parent is the parent viewGroup
     * @param viewType is the type of the View
     * @return the view holder of the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_scene, parent, false)
        return ViewHolder(view)
    }

    /**
     * It sets the data to each view
     * @param holder is the view holder of that view
     * @param position is the position of the view to render
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = scenes[position]
        holder.textNameScene.text = item.actors[0]
        holder.textLocation.text = item.scene_dsc
//        if (context != null) {
//            Glide.with(context).load(item.urlBackground).centerCrop().into(holder.imageViewBackground)
//            Glide.with(context).load(item.urlIcon).centerCrop().into(holder.imageViewIcon)
//        }
        with(holder.cardView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    /**
     * gets the number of views
     * @return the number of views
     */
    override fun getItemCount(): Int {
        Log.d("Scenes: ", scenes.size.toString())
        return scenes.size
    }

    /**
     * sets the new data and notifies to the adapter to refresh if necessary
     * @param scenes is the new list of movies to set
     */
    fun setData(scenes: List<Scene>?) {
        this.scenes = scenes!!
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class to save the refereces to the views of each view
     * @param mView is the general view
     * @property textViewName is the textView where will render the category name
     */
    inner class ViewHolder (val mView: View) : RecyclerView.ViewHolder(mView) {
        val textNameScene: TextView = mView.findViewById(R.id.textNameScene)
        val textLocation: TextView = mView.findViewById(R.id.textLocation)
        val cardView: CardView = mView.findViewById(R.id.cardViewBackground)

        /**
         * General function that returns the string
         */
        override fun toString(): String {
            return super.toString()
        }
    }

}
