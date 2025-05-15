package com.cx.mall.goods.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.cx.mall.common.util.ResponseVO;
import com.cx.mall.goods.model.Product;
import com.cx.mall.goods.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods/product")
public class ProductController {
    @Autowired
    private IProductService productService;
    @GetMapping("/list")
    public ResponseVO page(Product product){
        IPage page = productService.page(product);
        return ResponseVO.success(page);
    }
}
