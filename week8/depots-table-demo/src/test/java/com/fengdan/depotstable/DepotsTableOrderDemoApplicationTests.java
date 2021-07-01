package com.fengdan.depotstable;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fengdan.depotstable.entity.Order;
import com.fengdan.depotstable.mapper.OrderMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {DepotsTableOrderDemoApplication.class})
class DepotsTableOrderDemoApplicationTests {


    @Autowired
    public OrderMapper orderMapper;

    /**
     * aad订单
     */
    @Test
    public void addOrder() {
        for (int i = 1; i < 100; i++) {
            Order order1 = new Order();
            order1.setUserId(i * 3);
            if (i % 16 == 0) {
                order1.setStatus("fail");
            } else {
                order1.setStatus("ok");
            }
            orderMapper.insert(order1);
        }
    }

    /**
     * 获取一个订单
     */
    @Test
    public void getOrder() {
        Order order = orderMapper.selectById(1409341216864124930L);
        System.out.println(order);
    }

    /**
     * 获取用户订单列表
     */
    @Test
    public void getOrderLists() {
        orderMapper.selectList(new QueryWrapper<Order>().eq("user_id", 1))
                .forEach(System.out::println);
    }

    /**
     * 删除一个订单
     */
    @Test
    public void deleteOrder() {
        orderMapper.deleteById(1409342228790374402L);
    }


    /**
     * 更新一个订单
     */
    @Test
    public void updateOrder() {
        Order order = new Order();
        order.setOrderId(1409341216864124930L);
        order.setStatus("'fail'");
        orderMapper.updateById(order);
    }

}
