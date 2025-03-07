package com.codepath.articlesearch
import android.app.Application
import com.codepath.articlesearch.AppDatabase

class MealsApplication : Application() {
    val db by lazy { AppDatabase.getInstance(this) }
}