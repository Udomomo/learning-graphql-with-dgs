package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.GithubUser
import org.springframework.data.mongodb.repository.MongoRepository

interface UserRepository : MongoRepository<GithubUser, String> {
    fun findByGithubLogin(githubLogin: String): GithubUser
}
