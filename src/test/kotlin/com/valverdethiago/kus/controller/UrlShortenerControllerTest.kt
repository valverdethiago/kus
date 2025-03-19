package com.valverdethiago.kus.controller

import com.valverdethiago.kus.model.UrlTuple
import com.valverdethiago.kus.service.HashService
import com.valverdethiago.kus.service.UrlService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.http.HttpStatus
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class UrlShortenerControllerTest {

    private lateinit var mockMvc: MockMvc
    private lateinit var urlService: UrlService
    private lateinit var hashService: HashService
    private lateinit var controller: UrlShortenerController

    @BeforeEach
    fun setUp() {
        urlService = mock(UrlService::class.java)
        hashService = mock(HashService::class.java)
        controller = UrlShortenerController(urlService, hashService)
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build()
    }

    @Test
    fun `addUrl should return bad request for invalid URL`() {
        mockMvc.perform(post("/")
            .param("url", "invalid-url"))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("Invalid URL provided"))
    }

    @Test
    fun `addUrl should successfully create short URL`() {
        val originalUrl = "https://example.com"
        val hash = "abc123"
        val urlTuple = UrlTuple(hash, originalUrl)

        `when`(hashService.hash(originalUrl)).thenReturn(hash)
        `when`(urlService.save(hash, originalUrl)).thenReturn(urlTuple)

        mockMvc.perform(post("/")
            .param("url", originalUrl))
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.hash").value(hash))
            .andExpect(jsonPath("$.originalUrl").value(originalUrl))
            .andExpect(jsonPath("$.shortUrl").value("/go/$hash"))
    }

    @Test
    fun `redirectToUrl should return 404 for non-existent hash`() {
        val hash = "nonexistent"
        `when`(urlService.findByHash(hash)).thenReturn(null)

        mockMvc.perform(get("/go/$hash"))
            .andExpect(status().isNotFound)
            .andExpect(jsonPath("$.error").value("URL not found"))
    }

    @Test
    fun `redirectToUrl should redirect to original URL`() {
        val hash = "abc123"
        val originalUrl = "https://example.com"
        val urlTuple = UrlTuple(hash, originalUrl)

        `when`(urlService.findByHash(hash)).thenReturn(urlTuple)

        mockMvc.perform(get("/go/$hash"))
            .andExpect(status().isFound)
            .andExpect(header().string("Location", originalUrl))
    }

    @Test
    fun `addUrl should handle null URL parameter`() {
        mockMvc.perform(post("/"))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("Invalid URL provided"))
    }

    @Test
    fun `addUrl should handle empty URL parameter`() {
        mockMvc.perform(post("/")
            .param("url", ""))
            .andExpect(status().isBadRequest)
            .andExpect(jsonPath("$.error").value("Invalid URL provided"))
    }
} 