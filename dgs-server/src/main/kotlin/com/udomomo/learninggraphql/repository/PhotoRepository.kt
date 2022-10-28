package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.Photo
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PhotoRepository : MongoRepository<Photo, ObjectId> {
    fun findByIdIn(ids: List<ObjectId>): List<Photo>
    fun findByGithubLogin(githubLogin: String): List<Photo>
}
