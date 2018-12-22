package org.celtric.experiments.chip8

import java.io.BufferedInputStream
import java.io.DataInputStream

fun main(args: Array<String>) {
    App().run("/roms/maze.ch8")
}

class App {
    fun run(romLocation: String) {
        val instructions = ROM(romLocation).read()
        instructions.debug()
    }
}

class ROM(private val filename: String) {

    fun read(): Instructions {
        val romContent = romContent()
        val opcodes = mutableListOf<Opcode>()
        for (i in 0..(romContent.size - 1) step 2) {
            opcodes.add(Math.floor((i / 2).toDouble()).toInt(), Opcode(romContent[i], romContent[i + 1]))
        }
        return Instructions(opcodes)
    }

    private fun romContent() =
        DataInputStream(BufferedInputStream(javaClass.getResourceAsStream(filename))).use { it.readBytes() }
}

class Instructions(private val opcodes: List<Opcode>) {

    fun debug() {
        opcodes.forEach { it.debug() }
    }
}

class Opcode(private val mostSignificantByte: Byte, private val leastSignificantByte: Byte) {

    fun debug() {
        val upperNibble = mostSignificantByte.upperNibble()

        when (upperNibble) {
            0x6 -> println("Store 0x${leastSignificantByte.toHex()} in register v${mostSignificantByte.lowerNibble()}")
            else -> println("Unknown instruction $upperNibble")
        }
    }
}

fun Byte.upperNibble() = (toInt() and 0xf0) shr 4
fun Byte.lowerNibble() = toInt() and 0xf
fun Byte.toHex() = Integer.toHexString(toInt())
