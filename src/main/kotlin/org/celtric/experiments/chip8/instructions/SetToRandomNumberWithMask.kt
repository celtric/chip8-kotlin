package org.celtric.experiments.chip8.instructions

internal class SetToRandomNumberWithMask(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val mask = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0xC)
    }

    override fun debug() = DebugInfo("Set $register to a random number with a mask of $mask")
}
