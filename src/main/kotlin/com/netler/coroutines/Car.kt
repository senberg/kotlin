package com.netler.coroutines

import kotlinx.coroutines.delay
import kotlin.random.Random

class Car(val road: Road) {
    var left = Random.nextBoolean();
    var position = Random.nextInt(128);
    var crashed = false;

    suspend fun startDriving() {
        while(!crashed) {
            // Sometimes, change direction
            if (Random.nextInt(5) == 0) {
                left = !left;
            }

            if (left) {
                road.driveLeft(this);
            } else {
                road.driveRight(this);
            }

            delay(Random.nextInt(200).toLong());
        }
    }

    fun crash() {
        crashed = true;
    }

    init {
        road.add(this);
    }
}