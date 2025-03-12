package com.valverdethiago.kus.controller

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/")
class UrlShortenerController {

    @GetMapping("")
    fun addUrl(@RequestParam("url") url: String?): String {
        // Placeholder response
        return "URL received: $url (Implementation pending)"
    }

    @GetMapping("/go/{hash}")
    fun redirectToUrl(@PathVariable hash: String): String {
        // Placeholder response
        return "Redirecting to URL with hash: $hash (Implementation pending)"
    }
}
