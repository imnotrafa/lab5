package com.codepath.articlesearch.models

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.codepath.articlesearch.Meal
import com.codepath.articlesearch.models.MealEntity
import kotlinx.coroutines.flow.Flow

//Must change things
@Dao
interface MealDao {
    @Query("SELECT * FROM meal_table")
    fun getAll(): Flow<List<MealEntity>>

    @Insert
    fun insert(meal: MealEntity)

    @Query("DELETE FROM meal_table")
    fun deleteAll()
}