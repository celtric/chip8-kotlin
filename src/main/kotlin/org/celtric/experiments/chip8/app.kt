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
    }
}

class Memory {

    private val memoryLimit = 0x1000
    private val firstPublicLocation = 0x200

    private val state = ByteArray(memoryLimit)

    fun load(rom: ROM) {
        val romContent = rom.read()
        System.arraycopy(romContent, 0, state, firstPublicLocation, romContent.size)
    }
}

class ROM(private val filename: String) {
    fun read() = DataInputStream(BufferedInputStream(javaClass.getResourceAsStream(filename))).use { it.readBytes() }
}
