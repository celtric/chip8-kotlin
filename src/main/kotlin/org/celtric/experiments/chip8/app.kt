package org.celtric.experiments.chip8

import org.celtric.experiments.chip8.instructions.Instruction
import org.celtric.experiments.chip8.instructions.InstructionData
import org.celtric.experiments.chip8.instructions.Instructions
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
        val opcodes = mutableListOf<Instruction>()
        for (i in 0..(romContent.size - 1) step 2) {
            opcodes.add(
                Math.floor((i / 2).toDouble()).toInt(),
                Instruction.fromData(InstructionData(romContent[i], romContent[i + 1])))
        }
        return Instructions(opcodes)
    }

    private fun romContent() =
        DataInputStream(BufferedInputStream(javaClass.getResourceAsStream(filename))).use { it.readBytes() }
}
