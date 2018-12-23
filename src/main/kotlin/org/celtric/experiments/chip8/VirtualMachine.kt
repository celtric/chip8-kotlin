package org.celtric.experiments.chip8

import org.celtric.experiments.chip8.instructions.*
import org.celtric.experiments.chip8.instructions.Number
import org.celtric.experiments.chip8.ui.Display

val firstPublicAddress = MemoryAddress(0x200)

class VirtualMachine(private val instructions: Instructions, private val display: Display) {

    private val registers: MutableMap<Register, Number> = mutableMapOf()
    private val stack: MutableMap<Int, MemoryAddress> = mutableMapOf()

    private var programCounter = firstPublicAddress
    private var index = MemoryAddress(0)
    private var stackPointer = 0

    fun tick() {
        if (!instructions.exists(programCounter)) {
            println("No more instructions to execute")
        }
        val instruction = instructions.at(programCounter)
        println("Executing instruction at $programCounter ${instruction.javaClass.simpleName}")
        instruction.execute(this)
    }

    private fun advanceProgramCounter() {
        programCounter = programCounter.nextInstruction()
    }

    fun store(register: Register, value: Number) {
        registers[register] = value
        advanceProgramCounter()
    }

    fun increase(register: Register, by: Number) {
        registers[register] = registers[register]!! + by
        advanceProgramCounter()
    }

    fun setIndex(address: MemoryAddress) {
        index = address
        advanceProgramCounter()
    }

    fun skipNextIfRegisterEquals(register: Register, compareTo: Number) {
        if (registers[register] == compareTo) {
            advanceProgramCounter()
        }
        advanceProgramCounter()
    }

    // TODO: use height
    fun drawSprint(coordinate: RegisterCoordinate, height: Number) {
        display.draw(coordinate.resolve(registers))
        advanceProgramCounter()
    }

    fun jumpTo(address: MemoryAddress) {
        programCounter = address
    }

    fun copy(from: Register, to: Register) {
        registers[to] = registers[from]!!
        advanceProgramCounter()
    }

    fun execute(address: MemoryAddress) {
        stackPointer++
        stack[stackPointer] = programCounter
        programCounter = address
    }
}
