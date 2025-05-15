package com.cx.mall.goods.service.impl;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.cx.mall.goods.entity.Category;
import com.cx.mall.goods.mapper.CategoryMapper;
import com.cx.mall.goods.model.CategoryDTO;
import com.cx.mall.goods.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 商品类目 服务实现类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    @Override
    public List<CategoryDTO> listAll() {
        LambdaQueryChainWrapper<Category> queryOne = new
                LambdaQueryChainWrapper<>(baseMapper);
        // 1 查询一级分类
        List<Category> ones = queryOne.isNull(Category::getParentId).list();
        // 2 查询二级
        List<CategoryDTO> oneDTOs = ones.stream().map(one -> {
            LambdaQueryChainWrapper<Category> queryTwo = new
                    LambdaQueryChainWrapper<>(baseMapper);
            // 2.1 把一级分类转换成 DTO
            CategoryDTO oneDTO = JSON.parseObject(JSON.toJSONString(one),
                    CategoryDTO.class);
            // 2.2 查询二级分类
            List<Category> twos = queryTwo.eq(Category::getParentId,
                    oneDTO.getId()).list();
            // 3 查询三级分类
            List<CategoryDTO> twoDTOs = twos.stream().map(two -> {
                LambdaQueryChainWrapper<Category> queryThree = new
                        LambdaQueryChainWrapper<>(baseMapper);
                // 3.1 把二级分类转换成 DTO
                CategoryDTO twoDTO = JSON.parseObject(JSON.toJSONString(two),
                        CategoryDTO.class);
                // 3.2 查询三级分类
                List<Category> threes = queryThree.eq(Category::getParentId,
                        twoDTO.getId()).list();
                // 3.3 转换成 DTO
                List<CategoryDTO> threeDTOs = threes.stream().map(three ->
                                JSON.parseObject(JSON.toJSONString(three), CategoryDTO.class))
                        .collect(Collectors.toList());
                // 3.4 封装三级分类
                twoDTO.setCategoryList(threeDTOs);
                return twoDTO;
            }).collect(Collectors.toList());
            // 把二级分类转换成 DTO
            // 将二级分类封装到
            oneDTO.setCategoryList(twoDTOs);
            return oneDTO;
        }).collect(Collectors.toList());
        return oneDTOs;
    }



}
