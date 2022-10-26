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
                githubUser = photoInput.githubUser
            )
        )
    }

    fun listByUser(githubLogin: String): List<Photo> {
        return photoRepository.findByGithubUser(githubLogin)
    }

    fun list(): List<Photo> {
        return photoRepository.findAll()
    }
}
