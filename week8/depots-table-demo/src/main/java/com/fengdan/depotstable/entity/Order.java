package com.fengdan.depotstable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author fengdan
 * @date 2021年06月27日 22:46
 */
@Data
@TableName(value = "t_order")
public class Order {
    @TableId(type = IdType.ASSIGN_ID)
    private Long orderId;

    private Integer userId;

    private String status;
}