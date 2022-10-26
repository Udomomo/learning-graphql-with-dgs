package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.udomomo.learninggraphql.domain.PhotoCategory
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.PhotoService
import com.udomomo.learninggraphql.service.UserService
import org.assertj.core.api.Assertions.assertThat
import org.bson.types.ObjectId
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(classes = [DgsAutoConfiguration::class, PhotoDataFetcher::class])
class PhotoDataFetcherTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var photoService: PhotoService

    @MockBean
    lateinit var userService: UserService

    @BeforeEach
    fun before() {
        whenever(photoService.list()).thenReturn(listOf(Photo(ObjectId("1234567890abcdef12345678"), "dog", PhotoCategory.PORTRAIT, "my dog", "curl")))
        whenever(userService.findByLogin(any())).thenReturn(GithubUser(ObjectId("1234567890abcdef12345678"), "john", "John"))
    }

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

    @Test
    fun allPhotos() {
        val allPhotos: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            {
              allPhotos {
                name
                url
                postedBy {
                  name
                }
              }
            }
            """.trimIndent(),
            "data.allPhotos[0].postedBy.name"
        )
        assertThat(allPhotos).isEqualTo("John")
    }
}
