package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.dto.UserRequest
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.repository.TagRepository
import com.udomomo.learninggraphql.repository.UserRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository, val tagRepository: TagRepository) {
    fun saveUser(userInput: UserRequest): GithubUser {
        return userRepository.save(
            GithubUser(
                githubLogin = userInput.githubLogin,
                name = userInput.name
            )
        )
    }

    fun findByGithubLogin(githubLogin: String): GithubUser {
        return userRepository.findByGithubLogin(githubLogin)
    }

    fun listTaggedUsers(photoId: String): List<GithubUser> {
        val githubLogins = tagRepository.findByPhotoId(ObjectId(photoId)).map { it.githubLogin }
        return userRepository.findByGithubLoginIn(githubLogins)
    }
}
