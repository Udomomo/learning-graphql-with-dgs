package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.udomomo.learninggraphql.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [DgsAutoConfiguration::class, PhotoDataFetcher::class])
class PhotoDataFetcherTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var userService: UserService

    @Test
    fun totalPhotos() {
        val totalPhotos: Int = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            {
              totalPhotos
            }
            """.trimIndent(),
            "data.totalPhotos"
        )
        assertThat(totalPhotos).isEqualTo(42)
    }
}
