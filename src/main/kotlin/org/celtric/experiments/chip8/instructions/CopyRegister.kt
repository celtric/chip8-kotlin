package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class CopyRegister(instructionData: InstructionData) : Instruction() {

    private val from = instructionData.msbLowerNibble().toRegister()
    private val to = instructionData.lsbUpperNibble().toRegister()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x8)
    }

    override fun execute(vm: VirtualMachine) {
        vm.copy(from, to)
    }

    override fun debug(address: MemoryAddress) = DebugInfo(address, "Store the value of $from in $to")
}
