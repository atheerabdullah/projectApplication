package com.example.fproject.Controller;

import com.example.fproject.Model.MyOrder;
import com.example.fproject.Model.MyUser;
import com.example.fproject.Service.MyOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class MyOrderController {

    private final MyOrderService myOrderService;

    @GetMapping("/getAllOrder")
    public ResponseEntity getAllOrder(@AuthenticationPrincipal MyUser myUser) {
        List<MyOrder> myOrders = myOrderService.getAllOrder(myUser.getId());
        return ResponseEntity.status(200).body(myOrders);
    }

    @GetMapping("/getOrderById")
    public ResponseEntity getAllOrderById(@AuthenticationPrincipal MyUser myUser) {
        List<MyOrder> myOrders = myOrderService.getAllOrderById(myUser.getId());
        return ResponseEntity.status(200).body(myOrders);
    }

    @PostMapping("/addOrder")
    public ResponseEntity<MyOrder> addTotalPrice(@RequestBody MyOrder order) {
        MyOrder savedOrder = myOrderService.addTotalPrice(order);
        return ResponseEntity.ok(savedOrder);
    }

    @PutMapping("/{id}/changeOrderStatus")
    public ResponseEntity<String> changeOrderStatus(@PathVariable Integer id, @RequestBody String newStatus, @AuthenticationPrincipal MyUser myUser) {
        myOrderService.changeOrderStatus(id, newStatus, myUser);
        return ResponseEntity.status(200).body("Order with ID " + id + " status has been changed to " + newStatus);
    }

    @PutMapping("/updateOrder/{id}")
    public ResponseEntity updateOrder(@AuthenticationPrincipal MyUser myUser, @RequestBody MyOrder myOrder, @PathVariable Integer id) {
        myOrderService.updateOrder(myOrder, id, myUser.getId());
        return ResponseEntity.status(200).body("Order Updated");
    }


    @DeleteMapping("/deleteOrder/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Integer id) {
        myOrderService.deleteOrder(id);
        return ResponseEntity.ok("Order with ID " + id + " has been deleted");
    }

    @GetMapping("/getByid/{id}")
    public ResponseEntity findById(@PathVariable Integer id) {
        MyOrder myOrder = myOrderService.findMyOrderById(id);
        return ResponseEntity.status(200).body(myOrder);
    }

}
