package org.celtric.experiments.chip8.instructions

internal class JumpToAddress(instructionData: InstructionData) : Instruction() {

    private val address = instructionData.memoryAddress()

    companion object {
        val instructionCode = InstructionCode(0x1)
    }

    override fun debug() = DebugInfo("Jump to $address")
}
