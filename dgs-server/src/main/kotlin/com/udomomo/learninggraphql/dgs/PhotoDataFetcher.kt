package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment
import com.netflix.graphql.dgs.DgsQuery
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.UserService

@DgsComponent
class PhotoDataFetcher(val userService: UserService) {
    @DgsQuery
    fun totalPhotos(): Int = 42

    /**
     * Invoked only when Photo in query result contains url field.
     * See <a href="https://netflix.github.io/dgs/datafetching/#child-datafetchers">official doc</a>.
     */
    @DgsData(parentType = "Photo", field = "url")
    fun url(dfe: DgsDataFetchingEnvironment): String {
        val photo: Photo = dfe.getSource()
        return "http://yoursite.com/img/${photo.id}.jpg"
    }

    @DgsData(parentType = "Photo", field = "postedBy")
    fun postedBy(dfe: DgsDataFetchingEnvironment): GithubUser {
        val photo: Photo = dfe.getSource()
        return userService.findByLogin(photo.githubUser)
    }
}
