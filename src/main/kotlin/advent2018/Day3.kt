package advent2018

import java.io.File

fun main(args: Array<String>) {
    Day3Part1().exec()
    Day3Part2().exec()
}

class Day3Part1 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day3.txt")
        val list = mutableListOf<Square>()
        val matrix = Array(2000) { IntArray(2000) {0} }
        File(file.toURI()).forEachLine {
            val str1 = it.split("@")
            val id = str1[0].replace("#", "").trim().toInt()
            val str2 = str1[1].split(":")
            val edges = str2[0].split(",")
            val sizes = str2[1].split("x")
            val square = Square(id, edges[0].trim().toInt(), edges[1].toInt(), sizes[0].trim().toInt(), sizes[1].toInt())
            list.add(square)
        }
        for(it in list) {
            for(i in it.left until it.left + it.width) {
                for(j in it.top until it.top + it.height) {
                    var value = matrix[i][j]
                    matrix[i][j] = ++value
                }
            }
        }
        var result = 0
        matrix.forEach {
            it.forEach {
                if (it >= 2) {
                    result++
                }
            }
        }
        println("Result: $result")
    }
}

data class Square(val id: Int, val left: Int, val top: Int, val width: Int, val height: Int)

class Day3Part2 {
    fun exec() {
        val file = this::class.java.classLoader.getResource("day3.txt")
        val list = mutableListOf<Square>()
        val ids = mutableSetOf<Int>()
        val matrix = Array(2000) { Array<Pair<MutableSet<Int>, Int>>(2000) { Pair(mutableSetOf(), 0) } }
        File(file.toURI()).forEachLine {
            val str1 = it.split("@")
            val id = str1[0].replace("#", "").trim().toInt()
            ids.add(id)
            val str2 = str1[1].split(":")
            val edges = str2[0].split(",")
            val sizes = str2[1].split("x")
            val square = Square(id, edges[0].trim().toInt(), edges[1].toInt(), sizes[0].trim().toInt(), sizes[1].toInt())
            list.add(square)
        }
        for(it in list) {
            for(i in it.left until it.left + it.width) {
                for(j in it.top until it.top + it.height) {
                    val item = matrix[i][j]
                    val newItem = Pair(mutableSetOf(it.id), item.second + 1)
                    newItem.first.addAll(item.first)
                    matrix[i][j] = newItem
                    if (newItem.second > 1) {
                        for(id in newItem.first) {
                            ids.remove(id)
                        }
                    }
                }
            }
        }
        println(ids.toString())
    }
}