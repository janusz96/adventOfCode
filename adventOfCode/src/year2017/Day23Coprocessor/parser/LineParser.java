package year2017.Day23Coprocessor.parser;


import year2017.Day23Coprocessor.orders.*;
import year2017.Day23Coprocessor.programs.Program;
import year2017.Day23Coprocessor.registers.Register;
import year2017.Day23Coprocessor.registers.Registers;

public class LineParser {

    public static Order parseLine(String line, Program program){
        String[] tokens = line.split(" ");

        addRegisters(program.gerRegisters(), tokens);

        Order newOrder;
        if(tokens[0].equals("snd")){
            newOrder = new SendOrder(new Value(tokens[1]));
        } else if(tokens[0].equals("set")){
            newOrder = new SetOrder(tokens[1].charAt(0), new Value(tokens[2]));
        }  else if(tokens[0].equals("add")){
            newOrder = new AddOrder(tokens[1].charAt(0), new Value(tokens[2]));
        } else if(tokens[0].equals("mul")){
            newOrder = new MultiplyOrder(tokens[1].charAt(0), new Value(tokens[2]));
        } else if(tokens[0].equals("mod")){
            newOrder = new ModuloOrder(tokens[1].charAt(0), new Value(tokens[2]));
        } else if(tokens[0].equals("rcv")){
            newOrder = new ReceiveOrder(tokens[1].charAt(0));
        }  else if(tokens[0].equals("jgz")){
            newOrder = new JumpOrder(new Value(tokens[1]), new Value(tokens[2]), program);
        }   else if(tokens[0].equals("jnz")){
            newOrder = new JumpNotZeroOrder(new Value(tokens[1]), new Value(tokens[2]), program);
        }  else if(tokens[0].equals("sub")){
            newOrder = new SubOrder(tokens[1].charAt(0), new Value(tokens[2]));
        } else {
            throw new InvalidLineException(line);
        }

        return newOrder;
    }

    private static void addRegisters(Registers registers, String[] tokens) {
        if(tokens.length > 1 && !isInteger(tokens[1]))
            registers.addRegister(new Register(tokens[1].charAt(0)));
        if(tokens.length > 2 && !isInteger(tokens[2]))
            registers.addRegister(new Register(tokens[2].charAt(0)));
    }

    public static boolean isInteger(String input){
        try{
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }
}
