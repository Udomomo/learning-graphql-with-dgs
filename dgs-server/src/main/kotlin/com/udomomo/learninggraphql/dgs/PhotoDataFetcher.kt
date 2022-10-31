package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.udomomo.learninggraphql.dto.PhotoResponse
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.service.PhotoService
import com.udomomo.learninggraphql.service.UserService

@DgsComponent
class PhotoDataFetcher(val photoService: PhotoService, val userService: UserService) {
    @DgsQuery
    fun totalPhotos(): Int = 42

    @DgsQuery
    fun allPhotos(): List<PhotoResponse> {
        return photoService.listAll()
    }

    /**
     * Invoked only when Photo in query result contains url field.
     * See <a href="https://netflix.github.io/dgs/datafetching/#child-datafetchers">official doc</a>.
     */
    @DgsData(parentType = "Photo", field = "url")
    fun url(dfe: DgsDataFetchingEnvironment): String {
        val photoResponse: PhotoResponse = dfe.getSource()
        return "http://yoursite.com/img/${photoResponse.id}.jpg"
    }

    @DgsData(parentType = "Photo", field = "postedBy")
    fun postedBy(dfe: DgsDataFetchingEnvironment): GithubUser {
        val photoResponse: PhotoResponse = dfe.getSource()
        return userService.findByGithubLogin(photoResponse.githubLogin)
    }

    @DgsData(parentType = "Photo", field = "taggedUsers")
    fun taggedUsers(dfe: DgsDataFetchingEnvironment): List<GithubUser> {
        val photoResponse: PhotoResponse = dfe.getSource()
        return userService.listTaggedUsers(photoResponse.id)
    }
}
