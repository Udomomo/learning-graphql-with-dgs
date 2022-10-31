package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsMutation
import com.netflix.graphql.dgs.InputArgument
import com.udomomo.learninggraphql.dto.UserRequest
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.service.UserService

@DgsComponent
class UserMutation(val userService: UserService) {
    @DgsMutation
    fun postUser(@InputArgument("input") postUserInput: UserRequest): GithubUser {
        val result = userService.saveUser(postUserInput)

        println("User saved. $result")
        return result
    }
}
