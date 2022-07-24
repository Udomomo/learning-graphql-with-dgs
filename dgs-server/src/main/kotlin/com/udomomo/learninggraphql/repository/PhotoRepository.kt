package com.udomomo.learninggraphql.repository

import com.udomomo.learninggraphql.entity.Photo
import org.springframework.data.repository.CrudRepository

interface PhotoRepository : CrudRepository<Photo, Long>
