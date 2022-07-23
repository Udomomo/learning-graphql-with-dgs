package com.udomomo.learninggraphql.datafetcher

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, TotalPhotosDataFetcher::class])
class TotalPhotosDataFetcherTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

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
