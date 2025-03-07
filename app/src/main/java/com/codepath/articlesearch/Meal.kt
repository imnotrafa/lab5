package com.codepath.articlesearch

import android.support.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
@Keep
data class Meal(
    @SerialName("meal_title")
    val meal_title : String?,
    @SerialName("calories")
    val calorie_count : Int
)