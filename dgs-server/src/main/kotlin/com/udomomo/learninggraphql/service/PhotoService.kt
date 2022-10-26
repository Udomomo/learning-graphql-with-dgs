package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.domain.PostPhotoInput
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.repository.PhotoRepository
import com.udomomo.learninggraphql.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository, val userRepository: UserRepository) {
    fun savePhoto(photoInput: PostPhotoInput): Photo {
        return photoRepository.save(
            Photo(
                name = photoInput.name,
                category = photoInput.category,
                description = photoInput.description,
                githubLogin = photoInput.githubUser
            )
        )
    }

    fun listByGithubLogin(githubLogin: String): List<Photo> {
        return photoRepository.findByGithubLogin(githubLogin)
    }

    fun listAll(): List<Photo> {
        return photoRepository.findAll()
    }
}
