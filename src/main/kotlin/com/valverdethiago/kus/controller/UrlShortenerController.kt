package com.valverdethiago.kus.controller

import com.valverdethiago.kus.service.HashService
import com.valverdethiago.kus.service.UrlService
import com.valverdethiago.kus.service.UrlValidator
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/")
class UrlShortenerController(
    private val urlService: UrlService,
    private val hashService: HashService
) {

    @PostMapping("")
    fun addUrl(@RequestParam("url") url: String?): ResponseEntity<Any> {
        if (!UrlValidator.isValid(url)) {
            return ResponseEntity
                .badRequest()
                .body(mapOf("error" to "Invalid URL provided"))
        }

        val hash = hashService.hash(url!!)
        val urlTuple = urlService.save(hash, url)
        
        return ResponseEntity
            .ok()
            .body(mapOf(
                "hash" to urlTuple.hash,
                "originalUrl" to urlTuple.originalUrl,
                "shortUrl" to "/go/${urlTuple.hash}"
            ))
    }

    @GetMapping("/go/{hash}")
    fun redirectToUrl(@PathVariable hash: String): ResponseEntity<Any> {
        val urlTuple = urlService.findByHash(hash)
            ?: return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(mapOf("error" to "URL not found"))

        return ResponseEntity
            .status(HttpStatus.FOUND)
            .location(URI.create(urlTuple.originalUrl))
            .build()
    }
}
