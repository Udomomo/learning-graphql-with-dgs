package com.udomomo.learninggraphql.service

import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.repository.PhotoRepository
import org.springframework.stereotype.Service

@Service
class PhotoService(val photoRepository: PhotoRepository) {
    fun savePhoto(name: String, description: String?): Photo {
        return photoRepository.save(Photo(name = name, description = description))
    }
}
