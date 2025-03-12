package com.valverdethiago.kus.integration

import com.valverdethiago.kus.model.UrlTuple
import com.valverdethiago.kus.repository.UrlRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.cassandra.DataCassandraTest
import org.springframework.test.context.DynamicPropertySource
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.testcontainers.containers.CassandraContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@ExtendWith(SpringExtension::class)
@DataCassandraTest
@Testcontainers
class UrlRepositoryIT {

    @Autowired
    private lateinit var urlRepository: UrlRepository

    companion object {
        @Container
        val cassandraContainer = CassandraContainer("cassandra:4.1").apply {
            withExposedPorts(9042)
            start()
        }

        @JvmStatic
        @DynamicPropertySource
        fun cassandraProperties(registry: org.springframework.test.context.DynamicPropertyRegistry) {
            registry.add("spring.data.cassandra.contact-points") { cassandraContainer.host }
            registry.add("spring.data.cassandra.port") { cassandraContainer.getMappedPort(9042) }
        }
    }

    @Test
    fun `should save and retrieve UrlTuple`() {
        val hash = "testHash"
        val originalUrl = "https://test.com"
        val urlTuple = UrlTuple(hash, originalUrl)

        urlRepository.save(urlTuple)

        val retrieved = urlRepository.findById(hash)
        assertTrue(retrieved.isPresent)
        assertEquals(originalUrl, retrieved.get().originalUrl)
    }
}
