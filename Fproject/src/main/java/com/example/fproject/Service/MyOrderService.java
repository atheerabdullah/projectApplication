package com.example.fproject.Service;


import com.example.fproject.ApiException.ApiException;
import com.example.fproject.Model.MyOrder;
import com.example.fproject.Model.MyUser;
import com.example.fproject.Repository.MyOrderRepository;
import com.example.fproject.Repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyOrderService {


    private final MyOrderRepository myOrderRepository;
    private final ProductRepository productRepository;

    public List<MyOrder> getAllOrder(Integer id) {
        return myOrderRepository.findAllByUserId(id);
    }

    public List<MyOrder> getAllOrderById(Integer userId) {
        return myOrderRepository.findAllByUserId(userId);
    }

    public void updateOrder(MyOrder myOrder,Integer orderId,Integer userId) {
        MyOrder updateOrder = myOrderRepository.findMyOrderById(orderId);
        if (updateOrder == null) {
            throw new ApiException(" the Order Not found");
        }
        if (updateOrder.getUser().getId() != myOrder.getId()) {
            throw new ApiException("Error,Unauthorized");
        }
        updateOrder.setStatus(myOrder.getStatus());
        updateOrder.setQuantity(myOrder.getQuantity());
        updateOrder.setDateReceived(myOrder.getDateReceived());
        updateOrder.setTotalPrice(myOrder.getTotalPrice());
        myOrderRepository.save(updateOrder);
    }

    public MyOrder addTotalPrice(MyOrder order) {
        Integer totalPrice = order.getQuantity() * order.getProduct().getPrice();
        order.setTotalPrice(totalPrice);
        return myOrderRepository.save(order);
    }

    public void deleteOrder(Integer id) {
        MyOrder order = myOrderRepository.findMyOrderById(id);
        if (order != null) {
            if (order != null) {
                String orderStatus = order.getStatus();
                if (orderStatus.equals("IN_PROGRESS") || orderStatus.equals("COMPLETE"))
                    myOrderRepository.delete(order);
            } else {
                throw new EntityNotFoundException("Order with ID " + id + " not found");
            }
        }
    }
    public void changeOrderStatus(Integer id, String newStatus, MyUser currentUser){
        MyOrder order = myOrderRepository.findMyOrderById(id);
        if (order == null) {
            throw new ApiException("Order with ID " + id + " not found");
        }
        // Check if the user is an admin
        if (!currentUser.isAdmin()) {
            throw new ApiException("Only admin users can change the status of an order");
        }
        order.setStatus(newStatus);
        myOrderRepository.save(order);
    }

    public MyOrder findMyOrderById(Integer id){
        MyOrder order=myOrderRepository.findMyOrderById(id);
        if(order==null){
            throw new ApiException("Sorry, We Couldn't find the Order");
        }
        return order;
    }
}


