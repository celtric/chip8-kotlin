package org.celtric.experiments.chip8.instructions

internal class SkipNextInstructionIfRegisterValueEquals(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.lowerNibble().toRegister()
    private val compareTo = instructionData.leastSignificantByteAsNumber()

    companion object {
        val instructionCode = InstructionCode(0x3)
    }

    override fun debug() {
        println("Skip the following instruction if the value of register $register equals $compareTo")
    }
}
