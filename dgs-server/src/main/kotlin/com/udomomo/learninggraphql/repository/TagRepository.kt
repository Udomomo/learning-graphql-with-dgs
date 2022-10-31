package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.Tag
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TagRepository : MongoRepository<Tag, ObjectId> {
    fun findByGithubLogin(githubLogin: String): List<Tag>

    fun findByPhotoId(photoId: ObjectId): List<Tag>
}
