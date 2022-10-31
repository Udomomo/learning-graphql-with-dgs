package com.udomomo.learninggraphql.dto

import com.udomomo.learninggraphql.entity.Photo

class PhotoResponse(
    val id: String,
    val name: String,
    val category: PhotoCategory,
    val description: String?,
    val githubLogin: String,
    val taggedUsers: List<String>
) {
    companion object {
        fun of(photo: Photo): PhotoResponse {
            return PhotoResponse(
                id = photo.id.toString(),
                name = photo.name,
                category = photo.category,
                description = photo.description,
                githubLogin = photo.githubLogin,
                taggedUsers = photo.taggedUsers
            )
        }
    }
}
