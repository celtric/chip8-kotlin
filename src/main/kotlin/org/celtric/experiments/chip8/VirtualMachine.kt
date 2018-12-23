package org.celtric.experiments.chip8

import org.celtric.experiments.chip8.instructions.MemoryAddress
import org.celtric.experiments.chip8.instructions.Number
import org.celtric.experiments.chip8.instructions.Register

class VirtualMachine {

    private val registers: MutableMap<Register, Number> = mutableMapOf()
    private var index: MemoryAddress? = null

    fun store(register: Register, value: Number) {
        registers[register] = value
    }

    fun setIndex(address: MemoryAddress) {
        index = address
    }
}
