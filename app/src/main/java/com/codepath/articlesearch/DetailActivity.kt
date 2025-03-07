package com.codepath.articlesearch

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.codepath.articlesearch.models.MealEntity
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mealInputView: TextView
    private lateinit var calorieInputView: TextView

    private lateinit var addBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mealInputView = findViewById(R.id.mealTitleInput)
        calorieInputView = findViewById(R.id.caloriesInput)

        addBtn = findViewById<Button>(R.id.addBtn)

        //Clicks on here and adds it to the database accordingly.
        addBtn.setOnClickListener(){
            var mealInputValue = mealInputView.text.toString()
            var calorieInputValue = calorieInputView.text.toString()
            //checkf if both are not empty
            if (mealInputValue.isNotEmpty() && calorieInputValue.isNotEmpty()) {
                try {
                    // Ensure calorieInputValue is a valid integer
                    val calories = calorieInputValue.toIntOrNull()

                    if (calories != null) {
                        // Launching coroutine to insert into DB
                        lifecycleScope.launch(IO) {
                            (application as MealsApplication).db.mealDao().insert(
                                MealEntity(
                                    title = mealInputValue,
                                    calories = calorieInputValue
                                )
                            )
                            // After DB operation, switch to Main thread to update UI
                            withContext(Main) {
                                Log.d(TAG, "Success")
                                Toast.makeText(applicationContext, "Meal added successfully", Toast.LENGTH_SHORT).show()
                                finish()  // Close the current activity
                            }
                        }
                    } else {
                        // If calories input is invalid
                        Toast.makeText(applicationContext, "Please enter a valid calorie count", Toast.LENGTH_SHORT).show()
                    }

                } catch (e: Exception) {
                    Log.e(TAG, "Exception: ${e.message}", e)  // Log the stack trace as well for better debugging
                    Toast.makeText(applicationContext, "Error while adding meal", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(it.context, "Error: All fields must be filled", Toast.LENGTH_SHORT).show()
            }

        }

    }
}