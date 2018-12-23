package org.celtric.experiments.chip8.instructions

internal class StoreNumber(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.lowerNibble().toRegister()
    private val number = instructionData.leastSignificantByteAsNumber()

    companion object {
        val instructionCode = InstructionCode(0x6)
    }

    override fun debug() {
        println("Store $number in $register")
    }
}
