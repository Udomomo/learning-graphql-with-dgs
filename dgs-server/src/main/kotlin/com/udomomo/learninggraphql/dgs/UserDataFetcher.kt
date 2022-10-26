package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.PhotoService

@DgsComponent
class UserDataFetcher(val photoService: PhotoService) {
    @DgsData(parentType = "User", field = "postedPhotos")
    fun postedPhotos(dfe: DgsDataFetchingEnvironment): List<Photo> {
        val user: GithubUser = dfe.getSource()
        return photoService.listByGithubLogin(user.githubLogin)
    }
}
