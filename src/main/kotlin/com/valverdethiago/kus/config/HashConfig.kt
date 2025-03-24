package com.valverdethiago.kus.config

import com.valverdethiago.kus.service.HashAlgorithm
import com.valverdethiago.kus.service.HashService
import com.valverdethiago.kus.service.impl.XXHashServiceImpl
import com.valverdethiago.kus.service.impl.Murmur3HashServiceImpl
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "kus.hash")
class HashConfig {
    var algorithm: HashAlgorithm = HashAlgorithm.XXHASH

    @Bean
    fun hashService(
        xxHashService: XXHashServiceImpl,
        murmur3HashService: Murmur3HashServiceImpl
    ): HashService {
        return when (algorithm) {
            HashAlgorithm.XXHASH -> xxHashService
            HashAlgorithm.MURMUR3 -> murmur3HashService
            HashAlgorithm.FNV1A -> throw UnsupportedOperationException("FNV1A hash algorithm is not yet supported")
        }
    }
} 