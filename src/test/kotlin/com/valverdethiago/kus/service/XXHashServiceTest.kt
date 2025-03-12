package com.valverdethiago.kus.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class XXHashServiceTest {

    private lateinit var hashService: HashService

    @BeforeEach
    fun setUp() {
        hashService = XXHashService()
    }

    @Test
    fun `hash should produce consistent output for same input`() {
        val input = "test_string"
        val hash1 = hashService.hash(input)
        val hash2 = hashService.hash(input)

        assertEquals(hash1, hash2, "Hash should be deterministic")
    }

    @Test
    fun `hash should produce different outputs for different inputs`() {
        val hash1 = hashService.hash("string1")
        val hash2 = hashService.hash("string2")

        assertNotEquals(hash1, hash2, "Different inputs should produce different hashes")
    }

    @Test
    fun `hash should return a non-empty string`() {
        val hash = hashService.hash("non_empty_test")
        assertTrue(hash.isNotEmpty(), "Hash output should not be empty")
    }
}
