package com.udomomo.learninggraphql.mutation

import com.netflix.graphql.dgs.DgsComponent
import com.netflix.graphql.dgs.DgsData
import graphql.schema.DataFetchingEnvironment

@DgsComponent
class PostPhotoMutation {
    @DgsData(parentType = "Mutation", field = "postPhoto")
    fun postPhoto(dataFetchingEnvironment: DataFetchingEnvironment): Boolean {
        val name: String = dataFetchingEnvironment.getArgument("name")
        val description: String? = dataFetchingEnvironment.getArgument("description")
        println("name: $name, description: $description")
        return true
    }
}
