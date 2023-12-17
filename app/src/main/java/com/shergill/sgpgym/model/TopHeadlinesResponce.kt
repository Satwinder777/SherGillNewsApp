package com.shergill.sgpgym.model

data class TopHeadlinesResponce(
    val articles: List<ArticleX>,
    val status: String,
    val totalResults: Int
)