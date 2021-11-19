package com.example.movieplace.ui.home


import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.FragmentMovieBinding

class MyMoviesRecyclesViewAdapter(private val context: Context?) : RecyclerView.Adapter<MyMoviesRecyclesViewAdapter.ViewHolder>() {

    /**
     * Onclick to item. Updated when activitiesList developed
     */
//    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
//        val item = v.tag as Movie
//        val intent = Intent(context, Scenes::class.java)
//        intent.putExtra("movie", item.movie)
//        context?.startActivity(intent)
//    }
    private var movies: List<Movie> = ArrayList()


    /**
     * it inflates the view of each movie and sees the ViewHolder of the view
     * @param parent is the parent viewGroup
     * @param viewType is the type of the View
     * @return the view holder of the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.fragment_movie, parent, false)
        val binding = FragmentMovieBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    /**
     * It sets the data to each view
     * @param holder is the view holder of that view
     * @param position is the position of the view to render
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = movies[position]
        holder.textViewName.text = item.original_title
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
    override fun getItemCount(): Int {
        Log.d("Num", movies.size.toString())
        return movies.size
    }

    /**
     * sets the new data and notifies to the adapter to refresh if necessary
     * @param movies is the new list of movies to set
     */
    fun setData(movies: List<Movie>?) {
        this.movies = movies!!
        notifyDataSetChanged()
    }

    /**
     * ViewHolder class to save the refereces to the views of each view
     * @param mView is the general view
     * @property textViewName is the textView where will render the category name
     * @property imageViewBackground is the image to show behind the category
     * @property imageViewIcon is the icon of the category
     */
    inner class ViewHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val textViewName: TextView = binding.root.findViewById(R.id.textViewNameMovie)
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
