package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.udomomo.learninggraphql.domain.PostPhotoInput
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.PhotoService

@DgsComponent
class PhotoMutation(val photoService: PhotoService) {
    @DgsMutation
    fun postPhoto(@InputArgument("input") postPhotoInput: PostPhotoInput): Photo {
        val result = photoService.savePhoto(postPhotoInput)

        println("Photo saved. $result")
        return result
    }
}
