package advent2018

import java.io.File

fun main(args: Array<String>) {
    Day1Part1().exec()
    Day1Part2().exec()
}

class Day1Part1 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day1.txt")
        var result = 0
        File(file.toURI()).forEachLine {
           result += it.toInt()
        }
        println("result $result")
    }
}

class Day1Part2 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day1.txt")
        var frequency = 0
        val set = mutableSetOf<Int>()
        val list = mutableListOf<Int>()
        File(file.toURI()).forEachLine {
            list.add(it.toInt())
        }
        var counter = 0
        while (!set.contains(frequency)) {
            set.add(frequency)
            if (counter == list.size) {
                counter = 0
            }
            frequency += list[counter]
            counter++
        }
        println("result $frequency")
    }
}