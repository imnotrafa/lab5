package com.codepath.articlesearch

import DisplayMeal
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.articlesearch.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

private const val TAG = "MainActivity/"

class MainActivity : AppCompatActivity() {
    private val meals = mutableListOf<DisplayMeal>()
    private lateinit var binding: ActivityMainBinding
    private lateinit var addMealBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize view binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        // RecyclerView setup
        val mealsAdapter = MealAdapter(this, meals)
        binding.articles.adapter = mealsAdapter
        binding.articles.layoutManager = LinearLayoutManager(this)

        // Add item divider decoration
        val dividerItemDecoration = DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        binding.articles.addItemDecoration(dividerItemDecoration)

        // OnClickListener for adding meals
        binding.button.setOnClickListener {
            val intent = Intent(it.context, DetailActivity::class.java)
            it.context.startActivity(intent)
        }

        // Fetch meals from the database in a coroutine
        lifecycleScope.launch {
            try {
                (application as MealsApplication).db.mealDao().getAll().collect { databaseList ->
                    val mappedList = databaseList.map { entity ->
                        DisplayMeal(entity.title, entity.calories)
                    }

                    // Clear and update the list on the main thread
                    meals.clear()
                    meals.addAll(mappedList)

                    // Notify adapter that data has changed
                    mealsAdapter.notifyDataSetChanged()
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error fetching meals from the database: ${e.message}", e)
            }
        }

    }
}
