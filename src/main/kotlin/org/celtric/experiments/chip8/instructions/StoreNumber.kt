package org.celtric.experiments.chip8.instructions

internal class StoreNumber(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val number = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x6)
    }

    override fun debug() = DebugInfo("Store $number in $register")
}
