package com.valverdethiago.kus.model

import org.springframework.data.annotation.Id
import org.springframework.data.cassandra.core.mapping.PrimaryKey
import org.springframework.data.cassandra.core.mapping.Table

@Table("url_mapping")
data class UrlTuple(
    @PrimaryKey @Id val hash: String,
    val originalUrl: String
)