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
        // Application.launch(Display::class.java)
        val vm = VirtualMachine()
        instructions.execute(vm)
    }
}

class ROM(private val filename: String) {

    fun read(): Instructions {
        val romContent = romContent()
        val instructions = mutableListOf<Instruction>()
        for (i in 0..(romContent.size - 1) step 2) {
            instructions.add(Instruction.fromData(InstructionData(romContent[i], romContent[i + 1])))
        }
        return Instructions(instructions)
    }

    private fun romContent() =
        DataInputStream(BufferedInputStream(javaClass.getResourceAsStream(filename))).use { it.readBytes() }
}
