package com.netler.coroutines

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() : Unit = runBlocking {
    val road = Road();

    launch {
        while(true) {
            val car = Car(road);

            launch {
                car.startDriving();
            }

            delay(1000)
        }
    }

    launch {
        while(true) {
            println(road.toString())
            delay(100);
        }
    }
}

class Scraper {
}