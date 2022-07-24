package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.udomomo.learninggraphql.entity.Photo

@DgsComponent
class PhotoDataFetcher {
    @DgsQuery
    fun totalPhotos(): Int = 42

    @DgsData(parentType = "Photo", field = "url")
    fun url(dfe: DgsDataFetchingEnvironment): String {
        val photo: Photo = dfe.getSource()
        return "http://yoursite.com/img/${photo.id}.jpg"
    }
}
