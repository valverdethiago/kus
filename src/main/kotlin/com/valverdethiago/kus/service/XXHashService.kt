package com.valverdethiago.kus.service

import net.openhft.hashing.LongHashFunction
import org.springframework.stereotype.Service

@Service
class XXHashService : HashService {
    override fun hash(input: String): String {
        val hashValue = LongHashFunction.xx().hashChars(input)
        return hashValue.toString(16) // Convert to hexadecimal
    }
}
