package com.codepath.articlesearch

import DisplayMeal
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.AppDatabase

private const val TAG = "ArticleAdapter"

class MealAdapter(private val context: Context, private val meals: List<DisplayMeal>) :
    RecyclerView.Adapter<MealAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }

    override fun getItemCount() = meals.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mealTitleView = itemView.findViewById<TextView>(R.id.mealTitle)
        private val mealCaloriesView = itemView.findViewById<TextView>(R.id.calories)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(meal: DisplayMeal) {
            mealTitleView.text = meal.title
            mealCaloriesView.text = meal.calories

        }

        override fun onClick(v: View?) {
            // Get selected article

        }
    }
}