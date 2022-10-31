package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.dto.PhotoRequest
import com.udomomo.learninggraphql.dto.PhotoResponse
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.entity.Tag
import com.udomomo.learninggraphql.repository.PhotoRepository
import com.udomomo.learninggraphql.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository, val tagRepository: TagRepository) {
    fun savePhoto(photoInput: PhotoRequest): PhotoResponse {
        // TODO: throw custom exception if user in taggedUser doesn't exist
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
