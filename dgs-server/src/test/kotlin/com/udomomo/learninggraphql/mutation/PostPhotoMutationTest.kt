package com.udomomo.learninggraphql.mutation

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, PostPhotoMutation::class])
class PostPhotoMutationTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @Test
    fun postPhoto() {
        val result: Boolean = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation {
                postPhoto(name:"dog", description:"my dog")
            }
            """.trimIndent(),
            "data.postPhoto"
        )
        assertThat(result).isTrue
    }

    @Test
    fun postPhotoWithoutDescription() {
        val result: Boolean = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation {
                postPhoto(name:"dog")
            }
            """.trimIndent(),
            "data.postPhoto"
        )
        assertThat(result).isTrue
    }
}
