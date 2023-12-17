package com.shergill.sgpgym.model

data class EveryThingResponce(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)