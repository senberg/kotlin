package com.netler.game

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class LifeService {
    val life = Life();

    @GetMapping("/life/state")
    fun state() : State {
        return life.getState();
    }

    @GetMapping("/life/tick")
    fun tick() : State {
        life.tick();
        return state();
    }

    @GetMapping("/life/randomize")
    fun randomize() : State {
        life.randomize();
        return state();
    }

    @GetMapping("/life/flip")
    fun flip(@RequestParam("row") row: Int, @RequestParam("cell") cell : Int) : State {
        life.flip(row, cell);
        return state();
    }

    @GetMapping("/life/jump")
    fun jump(@RequestParam("target") target: Long) : State {
        life.jump(target);
        return state();
    }
}