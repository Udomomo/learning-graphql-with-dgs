package com.udomomo.learninggraphql.entity

import org.bson.types.ObjectId
import org.springframework.data.mongodb.core.mapping.Document

@Document("tags")
data class Tag(
    val photoId: ObjectId,
    val githubLogin: String
)
