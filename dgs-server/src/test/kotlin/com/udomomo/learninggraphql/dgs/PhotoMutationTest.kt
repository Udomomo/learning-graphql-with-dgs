package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.udomomo.learninggraphql.entity.Photo
import com.udomomo.learninggraphql.service.PhotoService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean

@SpringBootTest(
    classes = [
        DgsAutoConfiguration::class,
        PhotoMutation::class,
        PhotoDataFetcher::class // Added since tests for url use child datafetcher
    ]
)
class PhotoMutationTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var photoService: PhotoService

    @BeforeEach
    fun before() {
        Mockito.`when`(photoService.savePhoto(Mockito.anyString(), Mockito.anyString()))
            .thenReturn(Photo(1, "dog", "my dog"))
        Mockito.`when`(photoService.savePhoto(Mockito.anyString(), Mockito.eq(null))).thenReturn(Photo(1, "dog", null))
    }

    @Test
    fun postPhoto() {
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation {
                postPhoto(name:"dog", description:"my dog") {
                  id
                  name
                  description
                }
            }
            """.trimIndent(),
            "data.postPhoto.name"
        )
        assertThat(name).isEqualTo("dog")
    }

    @Test
    fun postPhotoWithoutDescription() {
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation {
                postPhoto(name:"dog") {
                    id
                    name
                    description
                }
            }
            """.trimIndent(),
            "data.postPhoto.name"
        )
        assertThat(name).isEqualTo("dog")
    }

    @Test
    fun postPhotoAndGetUrl() {
        val url: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation {
                postPhoto(name:"dog") {
                    id
                    url
                    name
                    description
                }
            }
            """.trimIndent(),
            "data.postPhoto.url"
        )
        assertThat(url).isEqualTo("http://yoursite.com/img/1.jpg")
    }
}
