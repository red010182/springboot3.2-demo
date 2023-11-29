package com.example.demo.models

import com.fasterxml.jackson.annotation.JsonSubTypes
import io.r2dbc.spi.Readable
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
fun toTime(str: String?): LocalDateTime? {
    if(str == null) return null
    return LocalDateTime.parse(str, formatter)
}

@Table("user")
data class User(
    @Id val id: Int,
    @Column("name") val name: String?,
    @Column("phone") val phone: String?,
    @Column("email") val email: String?,
    @Column("password") val password: String?,
    @Column("enable")  val enable: Int?,
    @Column("createdAt") val createdAt: LocalDateTime?,
    @Column("updatedAt") val updatedAt: LocalDateTime?,
    @Column("loginAt") val loginAt: LocalDateTime?,
) {
    companion object {
        fun fromRow(row: Readable): User {
            return User(
                id = row.get("id") as Int,
                name = row.get("name") as String?,
                phone = row.get("phone") as String?,
                email = row.get("email") as String?,
                password = row.get("password") as String?,
                enable = row.get("enable") as Int?,
                createdAt = null, //toTime(row.get("createdAt") as String?),
                updatedAt = null, //toTime(row.get("updatedAt") as String?),
                loginAt = null, //toTime(row.get("loginAt") as String?),
            )
        }
    }
}
