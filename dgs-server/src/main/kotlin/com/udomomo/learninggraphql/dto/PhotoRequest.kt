package com.udomomo.learninggraphql.dto

data class PhotoRequest(
    val name: String,
    val category: PhotoCategory,
    val description: String?,
    val githubUser: String,
    val taggedUsers: List<String>
)
