package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.udomomo.learninggraphql.domain.PhotoCategory
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

    @MockBean
    lateinit var userService: UserService

    @BeforeEach
    fun before() {
        // Mockito.any() fails in Kotlin, so we use mockito-kotlin here.
        // Create static ObjectId value by passing 24-byte hex string.
        whenever(photoService.savePhoto(any())).thenReturn(Photo(ObjectId("1234567890abcdef12345678"), "dog", PhotoCategory.PORTRAIT, "my dog", "curl", emptyList()))
    }

    @Test
    fun postPhoto() {
        // ${'$'} is a workaround to use dollar sign in raw string in Kotlin.
        // See https://kotlinlang.org/docs/strings.html#string-templates .
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation (${'$'}input: PostPhotoInput!) {
                postPhoto(input: ${'$'}input) {
                  id
                  name
                  category
                  description
                }
            }
            """.trimIndent(),
            "data.postPhoto.name",
            // Pass input for query in the `variables` argument.
            mapOf(
                "input" to mapOf("name" to "dog", "description" to "my dog", "githubUser" to "john")
            )
        )
        assertThat(name).isEqualTo("dog")
    }

    @Test
    fun postPhotoWithoutDescription() {
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation (${'$'}input: PostPhotoInput!) {
                postPhoto(input: ${'$'}input) {
                  id
                  name
                  category
                  description
                }
            }
            """.trimIndent(),
            "data.postPhoto.name",
            mapOf(
                "input" to mapOf("name" to "dog", "githubUser" to "john")
            )
        )
        assertThat(name).isEqualTo("dog")
    }

    @Test
    fun postPhotoAndGetUrl() {
        val url: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation (${'$'}input: PostPhotoInput!) {
                postPhoto(input: ${'$'}input) {
                  id
                  url
                  name
                  category
                  description
                }
            }
            """.trimIndent(),
            "data.postPhoto.url",
            mapOf(
                "input" to mapOf("name" to "dog", "description" to "my dog", "githubUser" to "john")
            )
        )
        assertThat(url).isEqualTo("http://yoursite.com/img/1234567890abcdef12345678.jpg")
    }
}
