package com.netler.game

import com.netler.game.Status.ALIVE
import com.netler.game.Status.DEAD
import java.util.concurrent.ConcurrentSkipListMap
import kotlin.random.Random

fun main() {
    println("Welcome to life")
    val life = Life();

    while(true){
        life.printBoard();
        print("> ");
        val input = readln();

        if(input == "r") {
            life.randomize();
        } else if(input.startsWith("j")) {
            val number = input.substring(input.lastIndexOf(' ') + 1);
            life.jump(number.toLong());
        } else if(input.startsWith("f")) {
            val row = input.substring(input.indexOf(' ') + 1, input.lastIndexOf(' '));
            val cell = input.substring(input.lastIndexOf(' ') + 1);
            life.flip(row.toInt(), cell.toInt());
        } else {
            life.tick();
        }
    }
}

class Life {
    private val size = 10;
    private var states = ConcurrentSkipListMap<Long, State>();
    private var iteration = 0L;
    private var board = Array(size) {
        Array(size) {
            if(Random.nextBoolean()) {
                ALIVE;
            } else {
                DEAD;
            }
        }
    }

    init{
        states[iteration] = State(board, iteration);
    }

    fun randomize() {
        for(i in 0..< board.size) {
            for(j in 0..< board.size) {
                board[i][j] = if (Random.nextBoolean()) {
                    ALIVE;
                } else {
                    DEAD;
                }
            }
        }

        states.put(iteration, State(board, iteration));
    }

    fun printBoard(){
        println("[iteration: ${iteration}]");
        println();

        for(row in board) {
            for(cell in row) {
                if(cell == ALIVE) {
                    print(" X ");
                } else {
                    print(" - ");
                }
            }

            println();
        }
    }

    fun tick() {
        val newBoard = Array(size) {
            val row = it;
            Array(size) {
                val cell = it;
                val status = board[row][cell];
                val neighbors = calculateNeighbours(board, row, cell);

                if(status == ALIVE) {
                    if(neighbors < 2) {
                        DEAD
                    } else if(neighbors > 3) {
                        DEAD
                    } else {
                        board[row][cell]
                    }
                } else {
                    if (neighbors == 3) {
                        ALIVE;
                    } else {
                        board[row][cell]
                    }
                }
            }
        }

        board = newBoard;
        iteration++;
    }


    private fun calculateNeighbours(board: Array<Array<Status>>, row: Int, cell: Int): Int {
        var count = 0;

        if(row > 0) {
            if(cell > 0 && board[row - 1][cell - 1] == ALIVE) {
                count++;
            }

            if(board[row - 1][cell] == ALIVE) {
                count++;
            }

            if(cell < size - 1 && board[row - 1][cell + 1] == ALIVE) {
                count++;
            }
        }

        if(cell > 0 && board[row][cell - 1] == ALIVE) {
            count++;
        }

        if(cell < size - 1 && board[row][cell + 1] == ALIVE) {
            count++;
        }

        if(row < size - 1) {
            if(cell > 0 && board[row + 1][cell - 1] == ALIVE) {
                count++;
            }

            if(board[row + 1][cell] == ALIVE) {
                count++;
            }

            if(cell < size - 1 && board[row + 1][cell + 1] == ALIVE) {
                count++;
            }
        }

        return count;
    }

    fun jump(target: Long){
        if(target < iteration) {
            val entry = states.floorEntry(target);

            for(key in states.keys) {
                if(key > entry.key) {
                    states.remove(key);
                }
            }

            board = entry.value.board;
            iteration = entry.value.iteration;
            val diff = target - entry.key;

            for(i in 0..< diff){
                tick();
            }
        } else if(target > iteration) {
            val diff = target - iteration;

            for(i in 0..< diff){
                tick();
            }
        }
    }

    fun flip(row : Int, cell : Int) {
        board[row][cell] = if(board[row][cell] == ALIVE)  {
            DEAD
        } else {
            ALIVE
        }

        states.put(iteration, State(board, iteration));
    }

    fun getState() : State {
        return State(board, iteration);
    }
}