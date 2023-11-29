package com.example.demo

import com.example.demo.models.User
import com.example.demo.repo.UserRepo
import io.r2dbc.spi.ConnectionFactories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.r2dbc.core.DatabaseClient
import org.springframework.r2dbc.core.flow
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class MainController(val repo: UserRepo, val client: DatabaseClient) {

    @GetMapping("/repoList")
    suspend fun repoList(): MutableList<User>? {
        return repo
            .findAll()
            .collectList()
            .awaitSingle()
    }
    @GetMapping("/clientList")
    suspend fun clientList(): MutableList<User>? {
        return client.sql("SELECT * FROM user")
            .map { row -> User.fromRow(row) }
            .all()
            .collectList()
            .awaitSingle()
    }
}