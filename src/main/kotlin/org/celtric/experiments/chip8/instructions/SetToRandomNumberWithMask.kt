package org.celtric.experiments.chip8.instructions

internal class SetToRandomNumberWithMask(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.lowerNibble().toRegister()
    private val mask = instructionData.leastSignificantByteAsNumber()

    companion object {
        val instructionCode = InstructionCode(0xC)
    }

    override fun debug() {
        println("Set $register to a random number with a mask of $mask")
    }
}
