package com.udomomo.learninggraphql.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("tags")
data class Tag(
    @Id
    val id: ObjectId = ObjectId.get(),
    val photoId: ObjectId,
    val userId: ObjectId
)
