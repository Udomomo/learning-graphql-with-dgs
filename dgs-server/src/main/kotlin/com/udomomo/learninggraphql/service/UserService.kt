package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.domain.PostUserInput
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {
    fun findByLogin(githubLogin: String): GithubUser {
        return userRepository.findByGithubLogin(githubLogin)
    }

    fun saveUser(userInput: PostUserInput): GithubUser {
        return userRepository.save(
            GithubUser(
                githubLogin = userInput.githubLogin,
                name = userInput.name
            )
        )
    }
}
