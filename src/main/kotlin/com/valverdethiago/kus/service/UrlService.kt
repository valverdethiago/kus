package com.valverdethiago.kus.service

import com.valverdethiago.kus.model.UrlTuple
import com.valverdethiago.kus.repository.UrlRepository
import org.springframework.stereotype.Service

@Service
class UrlService(private val urlRepository: UrlRepository) {

    fun save(hash: String, originalUrl: String): UrlTuple {
        val urlTuple = UrlTuple(hash, originalUrl)
        return urlRepository.save(urlTuple)
    }

    fun findByHash(hash: String): UrlTuple? {
        return urlRepository.findById(hash).orElse(null)
    }
}
