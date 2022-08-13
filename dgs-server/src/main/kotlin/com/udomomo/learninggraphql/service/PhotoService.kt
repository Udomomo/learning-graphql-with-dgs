package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.domain.PostPhotoInput
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.repository.PhotoRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository) {
    fun savePhoto(photoInput: PostPhotoInput): Photo {
        return photoRepository.save(
            Photo(
                name = photoInput.name,
                category = photoInput.category,
                description = photoInput.description
            )
        )
    }
}
