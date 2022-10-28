package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.domain.PostPhotoInput
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.repository.PhotoRepository
import com.udomomo.learninggraphql.repository.TagRepository
import org.bson.types.ObjectId
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository, val tagRepository: TagRepository) {
    fun savePhoto(photoInput: PostPhotoInput): Photo {
        // TODO: throw custom exception if user in taggedUser doesn't exist
        return photoRepository.save(
            Photo(
                name = photoInput.name,
                category = photoInput.category,
                description = photoInput.description,
                githubLogin = photoInput.githubUser,
                taggedUsers = photoInput.taggedUsers
            )
        )
    }

    fun listByGithubLogin(githubLogin: String): List<Photo> {
        return photoRepository.findByGithubLogin(githubLogin)
    }

    fun listAll(): List<Photo> {
        return photoRepository.findAll()
    }

    fun listTaggedPhotos(userId: ObjectId): List<Photo> {
        val photoIds = tagRepository.findByUserId(userId).map { it.photoId }
        return photoRepository.findByIdIn(photoIds)
    }
}
