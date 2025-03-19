package com.valverdethiago.kus.service

import com.valverdethiago.kus.model.UrlTuple
import com.valverdethiago.kus.repository.UrlRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
open class UrlService(private val urlRepository: UrlRepository) {

    @CacheEvict(value = ["urls"], key = "#hash")
    open fun save(hash: String, originalUrl: String): UrlTuple {
        val urlTuple = UrlTuple(hash, originalUrl)
        return urlRepository.save(urlTuple)
    }

    @Cacheable(value = ["urls"], key = "#hash", unless = "#result == null")
    open fun findByHash(hash: String): UrlTuple? {
        return urlRepository.findById(hash).orElse(null)
    }
}
