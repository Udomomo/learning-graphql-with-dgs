package com.udomomo.learninggraphql.domain

data class PostPhotoInput(
    val name: String,
    val category: PhotoCategory,
    val description: String?,
    val githubUser: String,
    val taggedUsers: List<String>
)
