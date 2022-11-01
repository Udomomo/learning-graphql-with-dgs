package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.dto.PhotoRequest
import com.udomomo.learninggraphql.dto.PhotoResponse
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.entity.Tag
import com.udomomo.learninggraphql.exception.UserNotFoundException
import com.udomomo.learninggraphql.repository.PhotoRepository
import com.udomomo.learninggraphql.repository.TagRepository
import com.udomomo.learninggraphql.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository, val userRepository: UserRepository, val tagRepository: TagRepository) {
    fun savePhoto(photoInput: PhotoRequest): PhotoResponse {
        val taggedUsers = photoInput.taggedUsers
        if (taggedUsers.isNotEmpty()) {
            val allUsers = userRepository.findAll().map { it.githubLogin }
            if (!allUsers.containsAll(taggedUsers)) throw UserNotFoundException("Some users do not exist")
        }

        val result = photoRepository.save(
            Photo(
                name = photoInput.name,
                category = photoInput.category,
                description = photoInput.description,
                githubLogin = photoInput.githubUser,
                taggedUsers = photoInput.taggedUsers
            )
        )

        photoInput.taggedUsers.forEach {
            tagRepository.save(
                Tag(result.id, it)
            )
        }

        return PhotoResponse.of(result)
    }

    fun listByGithubLogin(githubLogin: String): List<PhotoResponse> {
        return photoRepository.findByGithubLogin(githubLogin)
            .map { PhotoResponse.of(it) }
    }

    fun listAll(): List<PhotoResponse> {
        return photoRepository.findAll()
            .map { PhotoResponse.of(it) }
    }

    fun listTaggedPhotos(githubLogin: String): List<PhotoResponse> {
        val photoIds = tagRepository.findByGithubLogin(githubLogin).map { it.photoId }
        return photoRepository.findByIdIn(photoIds)
            .map { PhotoResponse.of(it) }
    }
}
