package com.cx.mall.goods.model;

import lombok.Data;

@Data
public class Stock {
    private Long skuId; // 库存Id
    private Integer num; // 数量
}
