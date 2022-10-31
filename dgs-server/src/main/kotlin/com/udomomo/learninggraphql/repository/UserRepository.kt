package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.GithubUser
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<GithubUser, ObjectId> {
    fun findByGithubLogin(githubLogin: String): GithubUser

    fun findByGithubLoginIn(githubLogins: List<String>): List<GithubUser>
}
