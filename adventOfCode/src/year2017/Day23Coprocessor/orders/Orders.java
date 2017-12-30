package year2017.Day23Coprocessor.orders;

import java.util.ArrayList;
import java.util.List;

public class Orders {
    private List<Order> orders;

    public Orders(){
        orders = new ArrayList<>();
    }

    public void addOrder(Order order){
        orders.add(order);
    }

    public Order getOrder(int index){
        return orders.get(index);
    }

    public int size() {
        return orders.size();
    }
}
