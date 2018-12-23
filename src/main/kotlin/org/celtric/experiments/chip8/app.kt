package org.celtric.experiments.chip8

import javafx.application.Application
import org.celtric.experiments.chip8.instructions.Instruction
import org.celtric.experiments.chip8.instructions.InstructionData
import org.celtric.experiments.chip8.instructions.Instructions
import org.celtric.experiments.chip8.ui.Display
import java.io.BufferedInputStream
import java.io.DataInputStream

fun main(args: Array<String>) {
    Application.launch(Display::class.java)
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
