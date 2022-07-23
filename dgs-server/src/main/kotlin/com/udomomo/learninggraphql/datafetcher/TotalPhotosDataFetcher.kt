package com.udomomo.learninggraphql.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class TotalPhotosDataFetcher {
    private val totalPhotos = 42

    @DgsQuery
    fun totalPhotos(): Int = 42
}
