package com.udomomo.learninggraphql.dgs

import com.netflix.graphql.dgs.DgsQueryExecutor
import com.netflix.graphql.dgs.autoconfig.DgsAutoConfiguration
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(classes = [DgsAutoConfiguration::class, PostPhotoMutation::class])
class PostPhotoMutationTest {
    @Autowired
    lateinit var dgsQueryExecutor: DgsQueryExecutor

    // @Test
    // fun postPhoto() {
    //     val names: List<String> = dgsQueryExecutor.executeAndExtractJsonPath(
    //         """
    //         mutation {
    //             postPhoto(name:"dog", description:"my dog") {
    //               id
    //               name
    //               description
    //             }
    //         }
    //         """.trimIndent(),
    //         "data.postPhoto[*].name"
    //     )
    //     assertThat(names).containsExactly("dog")
    // }
    //
    // @Test
    // fun postPhotoWithoutDescription() {
    //     val names: List<String> = dgsQueryExecutor.executeAndExtractJsonPath(
    //         """
    //         mutation {
    //             postPhoto(name:"dog") {
    //                 id
    //                 name
    //                 description
    //             }
    //         }
    //         """.trimIndent(),
    //         "data.postPhoto[*].title"
    //     )
    //     assertThat(names).containsExactly("dog")
    // }
}
