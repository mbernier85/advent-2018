package advent2018

import java.io.File

fun main(args: Array<String>) {
    Day2Part1().exec()
    Day2Part2().exec()
}

class Day2Part1 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day2.txt")
        var twice = 0
        var triple = 0
        File(file.toURI()).forEachLine {
            val map = mutableMapOf<Char, Int>()
            it.forEach { char ->
                var value: Int = map[char] ?: 0
                map[char] = ++value
            }
            if (map.filterValues {
                        it == 2
                    }.isNotEmpty()) {
                twice++
            }

            if (map.filterValues {
                        it == 3
                    }.isNotEmpty()) {
                triple++
            }
        }
        val result = twice * triple
        println("Result : $result")
    }
}

class Day2Part2 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day2.txt")
        val list = mutableListOf<String>()
        File(file.toURI()).forEachLine {
            list.add(it)
        }
        for (i in 0 until list.size) {
            for (j in 1 until list.size) {
                val first = list[i]
                val second = list[j]
                var diffs = 0
                for (k in 0 until first.length) {
                    if (first[k] != second[k]) {
                        diffs++
                    }
                }
                if (diffs == 1) {
                    var result = first
                    for (l in 0 until first.length) {
                        if (first[l] != second[l]) {
                            result = result.removeRange(l,l+1)
                        }
                    }
                    println("Results : $result")
                }
            }
        }
    }
}