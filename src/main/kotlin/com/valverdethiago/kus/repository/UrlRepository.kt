package com.valverdethiago.kus.repository

import com.valverdethiago.kus.model.UrlTuple
import org.springframework.data.cassandra.repository.CassandraRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlRepository : CassandraRepository<UrlTuple, String>
