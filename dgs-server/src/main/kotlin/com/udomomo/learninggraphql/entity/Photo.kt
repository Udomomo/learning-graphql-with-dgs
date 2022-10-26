package com.udomomo.learninggraphql.entity

import com.udomomo.learninggraphql.domain.PhotoCategory
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("photos")
data class Photo(
    @Id
    val id: ObjectId = ObjectId.get(),
    val name: String,
    val category: PhotoCategory,
    val description: String?,
    val githubLogin: String
)
