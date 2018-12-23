package org.celtric.experiments.chip8.instructions

import org.celtric.experiments.chip8.VirtualMachine
import org.celtric.experiments.chip8.ui.ScreenCoordinate
import kotlin.random.Random

class Instructions(private val instructions: Map<MemoryAddress, Instruction>) {

    fun debug() {
        instructions.forEach { address, instruction -> instruction.debug(address).print() }
    }

    fun exists(address: MemoryAddress) = instructions.contains(address)

    fun at(address: MemoryAddress) = instructions[address]!!
}

class InstructionData(private val mostSignificantByte: Byte, private val leastSignificantByte: Byte) {
    fun instructionCode() = msbUpperNibble().toInstructionCode()
    fun msbUpperNibble() = mostSignificantByte.upperNibble()
    fun msbLowerNibble() = mostSignificantByte.lowerNibble()
    fun lsbUpperNibble() = leastSignificantByte.upperNibble()
    fun lsbLowerNibble() = leastSignificantByte.lowerNibble()
    fun lsbAsNumber() = Number(leastSignificantByte.toInt())
    fun memoryAddress() = MemoryAddress(((mostSignificantByte.toInt() and 0xf) shl 8) or (leastSignificantByte.toInt() and 0xff))
}

data class InstructionCode(private val code: Int) {
    override fun toString(): String {
        return "InstructionCode(code=${code.toHex().toUpperCase()})"
    }
}

data class Nibble(private val value: Int) {
    fun toInstructionCode() = InstructionCode(value)
    fun toRegister() = Register(value)
    fun toNumber() = Number(value)
    fun toCoordinate(y: Nibble) = RegisterCoordinate(Register(value), Register(y.value))
}

data class Register(private val number: Int)

data class Number(private val value: Int) {

    fun toInt() = value

    operator fun plus(amount: Number) = Number(value + amount.value)

    companion object {
        // TODO: use mask
        fun random(mask: Number) = Number(Random.nextInt(0, 255))
    }
}

data class MemoryAddress(private val address: Int) {

    operator fun plus(amount: Int) = MemoryAddress(address + amount)

    fun nextInstruction() = MemoryAddress(address) + 2

    override fun toString(): String {
        return "MemoryAddress(address=0x${address.toHex()})"
    }
}

data class RegisterCoordinate(private val x: Register, private val y: Register) {
    fun resolve(registers: Map<Register, Number>) = ScreenCoordinate(registers[x]!!.toInt(), registers[y]!!.toInt())
}

abstract class Instruction {

    abstract fun execute(vm: VirtualMachine)
    abstract fun debug(address: MemoryAddress): DebugInfo

    companion object {
        fun fromData(data: InstructionData) = when (true) {
            JumpToAddress.matches(data) -> JumpToAddress(data)
            ExecuteSubroutine.matches(data) -> ExecuteSubroutine(data)
            SkipNextInstructionIfRegisterValueEquals.matches(data) -> SkipNextInstructionIfRegisterValueEquals(data)
            StoreNumber.matches(data) -> StoreNumber(data)
            IncreaseRegisterValue.matches(data) -> IncreaseRegisterValue(data)
            CopyRegister.matches(data) -> CopyRegister(data)
            SetIndex.matches(data) -> SetIndex(data)
            StoreRandomNumber.matches(data) -> StoreRandomNumber(data)
            DrawSprite.matches(data) -> DrawSprite(data)
            else -> UnknownInstruction(data)
        }
    }
}

class DebugInfo(private val address: MemoryAddress, private val description: String) {
    fun print() {
        println("$address - $description")
    }
}

private fun Int.toHex() = Integer.toHexString(this)
private fun Byte.toHex() = Integer.toHexString(toInt())
private fun Byte.upperNibble() = Nibble((toInt() and 0xf0) shr 4)
private fun Byte.lowerNibble() = Nibble(toInt() and 0xf)
