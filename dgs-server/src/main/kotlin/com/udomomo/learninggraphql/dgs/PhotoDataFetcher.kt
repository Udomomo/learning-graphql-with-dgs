package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class PhotoDataFetcher {
    @DgsQuery
    fun totalPhotos(): Int = 42
}
