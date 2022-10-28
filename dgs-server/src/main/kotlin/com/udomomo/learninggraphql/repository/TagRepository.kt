package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.Tag
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TagRepository : MongoRepository<Tag, ObjectId> {
    fun findByUserId(userId: ObjectId): List<Tag>

    fun findByPhotoId(photoId: ObjectId): List<Tag>
}
