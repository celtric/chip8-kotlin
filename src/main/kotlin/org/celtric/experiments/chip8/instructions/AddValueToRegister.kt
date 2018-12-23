package org.celtric.experiments.chip8.instructions

internal class AddValueToRegister(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val number = instructionData.lsbAsNumber()

    companion object {
        val instructionCode = InstructionCode(0x7)
    }

    override fun debug() = DebugInfo("Add $number to $register")
}
