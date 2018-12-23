package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine

internal class IncreaseRegisterValue(instructionData: InstructionData) : Instruction() {

    private val register = instructionData.msbLowerNibble().toRegister()
    private val by = instructionData.lsbAsNumber()

    companion object {
        fun matches(data: InstructionData) = data.instructionCode() == InstructionCode(0x7)
    }

    override fun execute(vm: VirtualMachine) {
        vm.increase(register, by)
    }

    override fun debug(address: MemoryAddress) = DebugInfo(address, "Increase $register by $by")
}
