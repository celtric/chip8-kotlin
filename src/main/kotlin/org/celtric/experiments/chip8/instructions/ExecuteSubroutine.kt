package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class ExecuteSubroutine(instructionData: InstructionData) : Instruction() {

    private val address = instructionData.memoryAddress()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x2)
    }

    override fun execute(vm: VirtualMachine) {
        vm.execute(address)
    }

    override fun debug() = DebugInfo("Execute subroutine starting at $address")
}
