package com.example.movieplace.ui.home


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieplace.R
import com.example.movieplace.data.model.Movie
import com.example.movieplace.databinding.FragmentMovieBinding
import com.example.movieplace.ui.scenes.ScenesActivity
import com.google.gson.GsonBuilder

class MyMoviesRecyclesViewAdapter(private val context: Context?) : RecyclerView.Adapter<MyMoviesRecyclesViewAdapter.ViewHolder>() {

    /**
     * Onclick to item. Updated when SceneFragment developed
     */
    private val mOnClickListener: View.OnClickListener = View.OnClickListener { v ->
        val item = v.tag as Movie
        val intent = Intent(context, ScenesActivity::class.java)
        intent.putExtra("movie", GsonBuilder().create().toJson(item))
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
        val num = item.vote_average
        val star = num/2
        holder.rating.rating = star.toFloat()
        if (context != null) {
            Glide.with(context).load(item.img).centerCrop().into(holder.movieImage)
        }
        with(holder.movieImage) {
            tag = item
            setOnClickListener(mOnClickListener)
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
     * @property movieImage is the image to show behind the movie
     * @property starRate1 is the first star
     * @property starRate2 is the second star
     * @property starRate3 is the third star
     * @property starRate4 is the fourth star
     * @property starRate5 is the fifth star
     */
    inner class ViewHolder(val binding: FragmentMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        val movieImage: ImageView = binding.root.findViewById(R.id.movieImage)
        val rating: RatingBar = binding.root.findViewById(R.id.ratingBar)

        /**
         * General function that returns the string
         */
        override fun toString(): String {
            return super.toString()
        }
    }

}
