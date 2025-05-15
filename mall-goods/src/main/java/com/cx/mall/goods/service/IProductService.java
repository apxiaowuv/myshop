package com.cx.mall.goods.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import com.cx.mall.goods.model.Product;

import java.util.List;

public interface IProductService  {

    IPage page(Product product);

}
