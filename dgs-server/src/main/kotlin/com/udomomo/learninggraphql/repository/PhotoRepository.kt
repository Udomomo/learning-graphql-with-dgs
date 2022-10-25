package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.Photo
import org.springframework.data.mongodb.repository.MongoRepository

interface PhotoRepository : MongoRepository<Photo, Long>
