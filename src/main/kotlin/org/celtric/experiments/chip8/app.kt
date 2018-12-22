package org.celtric.experiments.chip8

import java.io.BufferedInputStream
import java.io.DataInputStream

fun main(args: Array<String>) {
    App().run("/roms/maze.ch8")
}

class App {
    fun run(filename: String) {
        val memory = Memory()
        memory.load(ROM(filename))
        memory.debug()
    }
}

class Memory {

    private val memoryLimit = 0x1000
    private val firstPublicLocation = 0x200

    private val state = ByteArray(memoryLimit)
    private var programSize = 0

    fun load(rom: ROM) {
        val romContent = rom.read()
        programSize = romContent.size
        System.arraycopy(romContent, 0, state, firstPublicLocation, programSize)
    }

    fun debug() {
        for (address in firstPublicLocation..(firstPublicLocation + programSize) step 2) {
            opcodeOf(address).debug()
        }
    }

    private fun opcodeOf(address: Int): Opcode {
        require(address % 2 == 0) { "Least significant byte address provided" }
        return Opcode(state[address], state[address + 1])
    }
}

class ROM(private val filename: String) {
    fun read() = DataInputStream(BufferedInputStream(javaClass.getResourceAsStream(filename))).use { it.readBytes() }
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
