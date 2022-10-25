package com.udomomo.learninggraphql.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("githubusers")
class GithubUser(
    @Id
    val id: ObjectId = ObjectId.get(),
    @Indexed(unique = true)
    val githubLogin: String,
    val name: String
)

data class GithubLogin(
    val githubLogin: String
)
