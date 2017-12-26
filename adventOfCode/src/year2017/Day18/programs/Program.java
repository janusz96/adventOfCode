package year2017.Day18.programs;

import year2017.Day18.Printer;
import year2017.Day18.orders.Order;
import year2017.Day18.orders.Orders;
import year2017.Day18.orders.ReceiveOrder;
import year2017.Day18.parser.LineParser;
import year2017.Day18.registers.Register;
import year2017.Day18.registers.Registers;

import java.util.LinkedList;
import java.util.Queue;

public class Program {
    private Registers registers;
    private Orders orders;
    private int id;
    private Queue<Integer> queue;
    private int currentPosition;
    private boolean stop;


    public Program(int id){
        this.id = id;
        registers = new Registers();
        Register pRegister = new Register('p');
        pRegister.setValue(id);
        registers.addRegister(pRegister);
        orders = new Orders();
        queue = new LinkedList<>();
        currentPosition = 0;
        stop = false;
    }

    public void parseLine(String line){
        LineParser.parseLine(line, registers, orders);
    }
    public void executeOrders(){
        while(!stop){
            executeNextOrder();
        }
    }

    public void executeNextOrder(){
        if(stop)
            return;
        Order order = orders.getOrder(currentPosition);
        System.out.println(currentPosition + ": " + order.toString());
        if(order instanceof ReceiveOrder) {
            stop = true;
            System.out.println(registers.getLastPlayed());
        }
        order.execute(registers);
        Printer.printRegisters(registers);
        currentPosition++;
    }

    public void jump(int offset) {
        currentPosition += offset;
    }

}