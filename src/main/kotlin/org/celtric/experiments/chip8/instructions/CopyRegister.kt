package org.celtric.experiments.chip8.instructions

internal class CopyRegister(instructionData: InstructionData) : Instruction() {

    private val from = instructionData.msbLowerNibble().toRegister()
    private val to = instructionData.lsbUpperNibble().toRegister()

    companion object {
        val instructionCode = InstructionCode(0x8)
    }

    override fun debug() = DebugInfo("Store the value of $from in $to")
}
