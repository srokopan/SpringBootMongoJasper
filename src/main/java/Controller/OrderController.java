package Controller;

import Model.Order;
import Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value= "/api/mongo/order")
public class OrderController {

    @Autowired
    OrderService serv;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value= "/create")
    public String create(@RequestBody List<Order> ord) {
        logger.debug("Saving Orders.");
        serv.createOrder(ord);
        return "Employee records created.";
    }

    @GetMapping(value= "/getall")
    public Collection<Order> getAll() {
        logger.debug("Getting all orders.");
        return serv.getAllOrders();
    }

    @GetMapping(value= "/getbyid/{order-id}")
    public Optional<Order> getById(@PathVariable(value= "order-id") int id) {
        logger.debug("Getting order with order-id= {}.", id);
        return serv.findOrderById(id);
    }

    @PutMapping(value= "/update/{order-id}")
    public String update(@PathVariable(value= "order-id") int id,
                         @RequestBody Order e) {
        logger.debug("Updating employee with order-id= {}.", id);
        e.setOrderId(id);
        serv.updateOrder(e);
        return "Order record for order-id= " + id + " updated.";
    }

    @DeleteMapping(value= "/delete/{order-id}")
    public String delete(@PathVariable(value= "order-id") int id) {
        logger.debug("Deleting order with order-id= {}.", id);
        serv.deleteOrderById(id);
        return "Order record for order-id= " + id + " deleted.";
    }

    @DeleteMapping(value= "/deleteall")
    public String deleteAll() {
        logger.debug("Deleting all orders.");
        serv.deleteAllOrders();
        return "All order records deleted.";
    }
}