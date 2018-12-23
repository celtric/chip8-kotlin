package org.celtric.experiments.chip8.instructions

internal class StoreMemoryAddressInRegister(instructionData: InstructionData) : Instruction() {

    private val memoryAddress = instructionData.memoryAddress()

    companion object {
        val instructionCode = InstructionCode(0xA)
    }

    override fun debug() {
        println("Store memory address $memoryAddress in register ???")
    }
}
