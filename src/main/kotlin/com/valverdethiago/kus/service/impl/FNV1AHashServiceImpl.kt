package com.valverdethiago.kus.service.impl

import com.valverdethiago.kus.service.HashService
import net.openhft.hashing.LongHashFunction
import org.springframework.stereotype.Service

@Service
class FNV1AHashServiceImpl : HashService {
    override fun hash(input: String): String {
        val hashValue = LongHashFunction.fnv1a().hashChars(input)
        return hashValue.toString(16)
    }
} 