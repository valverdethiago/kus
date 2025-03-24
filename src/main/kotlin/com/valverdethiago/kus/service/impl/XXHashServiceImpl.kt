package com.valverdethiago.kus.service.impl

import com.valverdethiago.kus.service.HashService
import net.openhft.hashing.LongHashFunction
import org.springframework.stereotype.Service

@Service
class XXHashServiceImpl : HashService {
    override fun hash(input: String): String {
        val hashValue = LongHashFunction.xx().hashChars(input)
        return hashValue.toString(16)
    }
} 