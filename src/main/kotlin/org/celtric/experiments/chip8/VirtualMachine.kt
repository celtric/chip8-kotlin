package org.celtric.experiments.chip8

import org.celtric.experiments.chip8.instructions.*
import org.celtric.experiments.chip8.instructions.Number

class VirtualMachine {

    private val registers: MutableMap<Register, Number> = mutableMapOf()
    private var index: MemoryAddress? = null
    private var skipNextInstruction = false

    fun execute(instructions: Instructions) {
        instructions.all().forEach { execute(it) }
    }

    private fun execute(instruction: Instruction) {
        if (!skipNextInstruction) {
            instruction.execute(this)
        }
        skipNextInstruction = false
    }

    fun store(register: Register, value: Number) {
        registers[register] = value
    }

    fun setIndex(address: MemoryAddress) {
        index = address
    }

    fun skipNextIfRegisterEquals(register: Register, compareTo: Number) {
        if (registers[register] == compareTo) {
            skipNextInstruction = true
        }
    }
}
