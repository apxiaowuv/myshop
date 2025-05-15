package com.cx.mall.goods.model;

import com.cx.mall.goods.entity.SkuInfo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends SkuInfo {
    private Integer oneCategoryId; // 一级分类 ID
    private Integer twoCategoryId; // 二级分类 ID
    private Integer current; // 当前页
    private Integer size; // 页面大小
}
