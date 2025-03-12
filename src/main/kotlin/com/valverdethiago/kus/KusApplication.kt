package com.valverdethiago.kus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class KusApplication

fun main(args: Array<String>) {
    runApplication<KusApplication>(*args)
}
