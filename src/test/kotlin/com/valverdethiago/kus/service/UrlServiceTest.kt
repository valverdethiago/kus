package com.valverdethiago.kus.service

import com.valverdethiago.kus.model.UrlTuple
import com.valverdethiago.kus.repository.UrlRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.boot.test.context.SpringBootTest
import java.util.*

@SpringBootTest
class UrlServiceTest {

    private lateinit var urlRepository: UrlRepository
    private lateinit var urlService: UrlService

    @BeforeEach
    fun setUp() {
        urlRepository = mock(UrlRepository::class.java)
        urlService = UrlService(urlRepository)
    }

    @Test
    fun `save should store URL successfully`() {
        val hash = "abc123"
        val originalUrl = "https://example.com"
        val urlTuple = UrlTuple(hash, originalUrl)

        `when`(urlRepository.save(urlTuple)).thenReturn(urlTuple)

        val result = urlService.save(hash, originalUrl)
        assertEquals(urlTuple, result)
    }

    @Test
    fun `findByHash should return existing URL`() {
        val hash = "abc123"
        val originalUrl = "https://example.com"
        val urlTuple = UrlTuple(hash, originalUrl)

        `when`(urlRepository.findById(hash)).thenReturn(Optional.of(urlTuple))

        val result = urlService.findByHash(hash)
        assertNotNull(result)
        assertEquals(originalUrl, result?.originalUrl)
    }
}
