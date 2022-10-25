package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import com.udomomo.learninggraphql.entity.GithubUser
import com.udomomo.learninggraphql.service.UserService
import org.assertj.core.api.Assertions
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
        UserMutation::class
    ]
)
class UserMutationTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    @MockBean
    lateinit var userService: UserService

    @BeforeEach
    fun before() {
        // Mockito.any() fails in Kotlin, so we use mockito-kotlin here.
        // Create static ObjectId value by passing 24-byte hex string.
        whenever(userService.saveUser(any())).thenReturn(GithubUser(ObjectId("1234567890abcdef12345678"), "john", "John"))
    }

    @Test
    fun postUser() {
        // ${'$'} is a workaround to use dollar sign in raw string in Kotlin.
        // See https://kotlinlang.org/docs/strings.html#string-templates .
        val name: String = dgsQueryExecutor.executeAndExtractJsonPath(
            """
            mutation (${'$'}input: PostUserInput!) {
                postUser(input: ${'$'}input) {
                  id
                  githubLogin
                  name
                }
            }
            """.trimIndent(),
            "data.postUser.name",
            // Pass input for query in the `variables` argument.
            mapOf(
                "input" to mapOf("githubLogin" to "john", "name" to "John")
            )
        )
        Assertions.assertThat(name).isEqualTo("John")
    }
}
