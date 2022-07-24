package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.PhotoService
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class PostPhotoMutation(val photoService: PhotoService) {
    @DgsMutation
    fun postPhoto(dataFetchingEnvironment: DataFetchingEnvironment): Photo {
        val name: String = dataFetchingEnvironment.getArgument("name")
        val description: String? = dataFetchingEnvironment.getArgument("description")

        val result = photoService.savePhoto(name, description)

        println("Photo saved. $result")
        return result
    }
}
