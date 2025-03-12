package com.valverdethiago.kus.service

import java.net.URI

object UrlValidator {
    fun isValid(url: String?): Boolean {
        if (url.isNullOrBlank()) return false

        return try {
            val uri = URI(url)
            val scheme = uri.scheme?.lowercase()
            scheme == "http" || scheme == "https"
        } catch (e: Exception) {
            false
        }
    }
}
