package com.example.twitter_like.data.request.conversation

import java.util.UUID

data class Create(
    val username: Array<UUID>
)

data class Update(
    val username: Array<UUID>
)

data class Delete(
    val conversationId: UUID
)