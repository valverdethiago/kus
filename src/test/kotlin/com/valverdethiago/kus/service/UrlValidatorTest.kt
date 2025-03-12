package com.valverdethiago.kus.service

import com.valverdethiago.kus.service.UrlValidator
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class UrlValidatorTest {

    @Test
    fun `valid HTTP and HTTPS URLs should return true`() {
        assertTrue(UrlValidator.isValid("http://example.com"))
        assertTrue(UrlValidator.isValid("https://example.com"))
        assertTrue(UrlValidator.isValid("https://www.google.com"))
    }

    @Test
    fun `invalid URLs should return false`() {
        assertFalse(UrlValidator.isValid("ftp://example.com"))
        assertFalse(UrlValidator.isValid("file:///etc/passwd"))
        assertFalse(UrlValidator.isValid("htt://wrong.com"))
        assertFalse(UrlValidator.isValid("https//missing-colon.com"))
        assertFalse(UrlValidator.isValid("://missing-schema.com"))
        assertFalse(UrlValidator.isValid("invalid-url"))
    }

    @Test
    fun `empty or null URLs should return false`() {
        assertFalse(UrlValidator.isValid(null))
        assertFalse(UrlValidator.isValid(""))
        assertFalse(UrlValidator.isValid("   "))
    }
}
