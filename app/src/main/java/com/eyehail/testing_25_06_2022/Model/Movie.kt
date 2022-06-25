package com.eyehail.testing_25_06_2022.Model

data class Movie(
    val copyright: String,
    val has_more: Boolean,
    val num_results: Int,
    val results: List<Result>,
    val status: String
)