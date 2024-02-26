package com.netler.game

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameApplication

fun main(args: Array<String>) {
	println("Hello world ...")
	runApplication<GameApplication>(*args)
}
