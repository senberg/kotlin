package com.netler.coroutines

class Road {
    val positions = Array<HashSet<Car>>(128) { i -> HashSet() };

    fun add(car: Car) {
        positions[car.position].add(car);
    }


    override fun toString() : String{
        val result = StringBuilder();

        positions.forEach{
            if(it.isEmpty()) {
                result.append("-")
            } else if (it.size == 1){
                result.append("C")
            } else {
                result.append("X")
            }
        };

        return result.toString();
    }

    fun driveLeft(car: Car) {
        if(car.position != 0) {
            positions[car.position].remove(car);
            car.position--;
            positions[car.position].add(car);
            checkCrash(car.position);
        }
    }

    fun driveRight(car: Car) {
        if(car.position != 127) {
            positions[car.position].remove(car);
            car.position++;
            positions[car.position].add(car);
            checkCrash(car.position);
        }
    }

    private fun checkCrash(position: Int) {
        if(positions[position].size > 1) {
            positions[position].forEach() {
                it.crash();
            }
        }
    }
}