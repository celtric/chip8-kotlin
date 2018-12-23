package org.celtric.experiments.chip8.instructions

internal class ExecuteSubroutine(instructionData: InstructionData) : Instruction() {

    private val address = instructionData.memoryAddress()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x2)
    }

    override fun debug() = DebugInfo("Execute subroutine starting at $address")
}
