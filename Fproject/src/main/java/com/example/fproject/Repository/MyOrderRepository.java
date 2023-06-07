package com.example.fproject.Repository;

import com.example.fproject.Model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder,Integer> {

    MyOrder findMyOrderById(Integer id);
    List<MyOrder> findAllByUserId(Integer id);


}
