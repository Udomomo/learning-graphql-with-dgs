package com.udomomo.learninggraphql.exception

import com.netflix.graphql.types.errors.TypedGraphQLError
import graphql.execution.DataFetcherExceptionHandler
import graphql.execution.DataFetcherExceptionHandlerParameters
import graphql.execution.DataFetcherExceptionHandlerResult
import org.springframework.stereotype.Component
import java.util.concurrent.CompletableFuture

@Component
class CustomDataFetcherExceptionHandler : DataFetcherExceptionHandler {
    @Override
    override fun handleException(handlerParameters: DataFetcherExceptionHandlerParameters?): CompletableFuture<DataFetcherExceptionHandlerResult> {
        val exception = handlerParameters?.exception
        if (exception is UserNotFoundException) {
            val graphqlError = TypedGraphQLError.newInternalErrorBuilder()
                .message(exception.errorMessage)
                .path(handlerParameters.path).build()

            return CompletableFuture.completedFuture(
                DataFetcherExceptionHandlerResult.newResult()
                    .error(graphqlError)
                    .build()
            )
        } else {
            return super.handleException(handlerParameters)
        }
    }
}

data class UserNotFoundException(val errorMessage: String) : RuntimeException()
