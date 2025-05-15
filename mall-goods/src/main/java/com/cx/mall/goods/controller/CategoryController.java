package com.cx.mall.goods.controller;

import com.cx.mall.common.util.ResponseVO;
import com.cx.mall.goods.model.CategoryDTO;
import com.cx.mall.goods.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品类目 前端控制器
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/goods/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/list")
    public ResponseVO<List<CategoryDTO>> list(){
        List<CategoryDTO> list = categoryService.listAll();
        return ResponseVO.success(list);
    }
}

