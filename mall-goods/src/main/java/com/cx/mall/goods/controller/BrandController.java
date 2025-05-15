package com.cx.mall.goods.controller;

import com.cx.mall.common.util.ResponseVO;
import com.cx.mall.goods.entity.Brand;
import com.cx.mall.goods.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 品牌表 前端控制器
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@RestController
@RequestMapping("/goods/brand")
public class BrandController {
    @Autowired
    private IBrandService brandService;
    /**
     * 查询所有品牌
     * @return
     */
    @GetMapping("/list")
    public ResponseVO<List<Brand>> list(){
        List<Brand> brands = brandService.list();
        return ResponseVO.success(brands);
    }
    /**
     * 按一级分类 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/category/1/{id}")
    public ResponseVO<List<Brand>> listByOneCategoryId(@PathVariable Integer id){
        List<Brand> brands = brandService.listByOneCategoryId(id);
        return ResponseVO.success(brands);
    }
    /**
     * 按二级分类 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/category/2/{id}")
    public ResponseVO<List<Brand>> listByTwoCategoryId(@PathVariable Integer id){
        List<Brand> brands = brandService.listByTwoCategoryId(id);
        return ResponseVO.success(brands);
    }
    /**
     * 按三级分类 ID 查询
     * @param id
     * @return
     */
    @GetMapping("/category/3/{id}")
    public ResponseVO<List<Brand>> listByThreeCategoryId(@PathVariable Integer
                                                                 id){
        List<Brand> brands = brandService.listByThreeCategoryId(id);
        return ResponseVO.success(brands);
    }

}
