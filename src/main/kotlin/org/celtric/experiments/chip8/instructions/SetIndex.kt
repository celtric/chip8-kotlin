package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class SetIndex(instructionData: InstructionData) : Instruction() {

    private val address = instructionData.memoryAddress()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0xA)
    }

    override fun execute(vm: VirtualMachine) {
        vm.setIndex(address)
    }

    override fun debug() = DebugInfo("Set index to $address")
}
