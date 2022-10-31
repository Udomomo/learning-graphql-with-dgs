package com.udomomo.learninggraphql.entity

import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("githubusers")
class GithubUser(
    @Indexed(unique = true)
    val githubLogin: String,
    val name: String
)
