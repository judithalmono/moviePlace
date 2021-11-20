package com.example.movieplace.ui.home


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.example.movieplace.data.model.Scene
import com.example.movieplace.databinding.FragmentMovieBinding
import kotlin.math.roundToInt

class MyMoviesRecyclesViewAdapter(private val context: Context?) : RecyclerView.Adapter<MyMoviesRecyclesViewAdapter.ViewHolder>() {

    /**
     * Onclick to item. Updated when activitiesList developed
     */
    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as Movie
        val intent = Intent(context, Scene::class.java)
        intent.putExtra("movie", item.original_title)
        context?.startActivity(intent)
    }
    private var movies: List<Movie> = ArrayList()


    /**
     * it inflates the view of each movie and sees the ViewHolder of the view
     * @param parent is the parent viewGroup
     * @param viewType is the type of the View
     * @return the view holder of the view
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
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
        rateStar(holder, item)
        if (context != null) {
            Glide.with(context).load(item.img).centerCrop().into(holder.movieImage)
        }
        with(holder.movieImage) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun rateStar(holder: ViewHolder, item: Movie) {
        val num = item.vote_average.roundToInt()
        val star = num/2
        Log.d(item.original_title, "$num $star")
        for (i in star..4) {
            when (i) {
                5 -> holder.starRate5.setImageDrawable(context?.let { getDrawable(it, R.drawable.starfill) })
                4 -> holder.starRate4.setImageDrawable(context?.let { getDrawable(it, R.drawable.starfill) })
                3 -> holder.starRate3.setImageDrawable(context?.let { getDrawable(it, R.drawable.starfill) })
                2 -> holder.starRate2.setImageDrawable(context?.let { getDrawable(it, R.drawable.starfill) })
                1 -> holder.starRate1.setImageDrawable(context?.let { getDrawable(it, R.drawable.starfill) })
            }
        }
        for (i in star..4) {
            Log.d(item.original_title, "$num $star $i")
            when (i) {
                4 -> holder.starRate5.setImageDrawable(context?.let { getDrawable(it, R.drawable.starempty) })
                3 -> holder.starRate4.setImageDrawable(context?.let { getDrawable(it, R.drawable.starempty) })
                2 -> holder.starRate3.setImageDrawable(context?.let { getDrawable(it, R.drawable.starempty) })
                1 -> holder.starRate2.setImageDrawable(context?.let { getDrawable(it, R.drawable.starempty) })
            }
        }
        when (num) {
            9 -> holder.starRate5.setImageDrawable(context?.let { getDrawable(it, R.drawable.starhalf) })
            7 -> holder.starRate4.setImageDrawable(context?.let { getDrawable(it, R.drawable.starhalf) })
            5 -> holder.starRate3.setImageDrawable(context?.let { getDrawable(it, R.drawable.starhalf) })
            3 -> holder.starRate2.setImageDrawable(context?.let { getDrawable(it, R.drawable.starhalf) })
            1 -> holder.starRate1.setImageDrawable(context?.let { getDrawable(it, R.drawable.starhalf) })
        }
    }

    /**
     * gets the number of views
     * @return the number of views
     */
    override fun getItemCount(): Int {
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
     * @param binding
     * @property textViewName is the textView where will render the movie name
     * @property movieImage is the image to show behind the movie
     * @property starRate1 is the first star
     * @property starRate2 is the second star
     * @property starRate3 is the third star
     * @property starRate4 is the fourth star
     * @property starRate5 is the fifth star
     */
    inner class ViewHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieImage: ImageView = binding.root.findViewById(R.id.movieImage)
        val starRate1: ImageView = binding.root.findViewById(R.id.starRate1)
        val starRate2: ImageView = binding.root.findViewById(R.id.starRate2)
        val starRate3: ImageView = binding.root.findViewById(R.id.starRate3)
        val starRate4: ImageView = binding.root.findViewById(R.id.starRate4)
        val starRate5: ImageView = binding.root.findViewById(R.id.starRate5)
        
        /**
         * General function that returns the string
         */
        override fun toString(): String {
            return super.toString()
        }
    }

}
