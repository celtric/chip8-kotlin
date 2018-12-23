package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class JumpToAddress(instructionData: InstructionData) : Instruction() {

    private val address = instructionData.memoryAddress()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x1)
    }

    override fun execute(vm: VirtualMachine) {
        TODO()
    }

    override fun debug() = DebugInfo("Jump to $address")
}
