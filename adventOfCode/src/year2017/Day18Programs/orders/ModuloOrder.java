package year2017.Day18Programs.orders;

import year2017.Day18Programs.programs.Program;
import year2017.Day18Programs.registers.Register;
import year2017.Day18Programs.registers.Registers;

public class ModuloOrder extends TwoArgumentsOrder {
    public ModuloOrder(Character destinationName, Value value) {
        super(destinationName, value);
    }

    @Override
    public void execute(Program program) {
        Registers registers = program.gerRegisters();
        Register destination = registers.getRegister(destinationName);
        destination.modulo(value.getValue(registers));
    }

    public String toString(){
        return "mod " + destinationName + " " + value.toString();
    }
}
