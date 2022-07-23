package com.udomomo.learninggraphql.datafetcher

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsQuery

@DgsComponent
class TotalPhotosDataFetcher {
    @DgsQuery
    fun totalPhotos(): Int = 42
}
