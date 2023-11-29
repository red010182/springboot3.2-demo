package com.example.demo.repo

import com.example.demo.models.User
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import reactor.core.publisher.Flux

interface UserRepo: ReactiveCrudRepository<User, Int> {
    override fun findAll(): Flux<User>
}