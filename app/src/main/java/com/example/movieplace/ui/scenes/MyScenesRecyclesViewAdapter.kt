package com.example.movieplace.ui.scenes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.Scene
import com.example.movieplace.ui.home.MyMoviesRecyclesViewAdapter

class MyScenesRecyclesViewAdapter(private val context: Context?) : RecyclerView.Adapter<MyScenesRecyclesViewAdapter.ViewHolder>() {

    /**
     * Onclick to item. Updated when activitiesList developed
     */
//    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
//        val item = v.tag as Movie
//        val intent = Intent(context, Scenes::class.java)
//        intent.putExtra("movie", item.movie)
//        context?.startActivity(intent)
//    }
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
        holder.textViewName.text = item.id
//        if (context != null) {
//            Glide.with(context).load(item.urlBackground).centerCrop().into(holder.imageViewBackground)
//            Glide.with(context).load(item.urlIcon).centerCrop().into(holder.imageViewIcon)
//        }
//        with(holder.imageViewBackground) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }

    /**
     * gets the number of views
     * @return the number of views
     */
    override fun getItemCount(): Int = scenes.size

    /**
     * sets the new data and notifies to the adapter to refresh if necessary
     * @param movies is the new list of movies to set
     */
    fun setData(movies: List<Movie>?) {
        this.scenes = scenes!!
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class to save the refereces to the views of each view
     * @param mView is the general view
     * @property textViewName is the textView where will render the category name
     * @property imageViewBackground is the image to show behind the category
     * @property imageViewIcon is the icon of the category
     */
    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val textViewName: TextView = mView.findViewById(R.id.textViewNameScene)
//        val imageViewBackground: ImageView = mView.findViewById(R.id.pinkBackground)
//        val imageViewIcon: ImageView = mView.findViewById(R.id.imageViewIconCategory)
        /**
         * General function that returns the string
         */
        override fun toString(): String {
            return super.toString()
        }
    }

}
