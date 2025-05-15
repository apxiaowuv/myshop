package com.cx.mall.goods.service;

import com.cx.mall.goods.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.mall.goods.model.CategoryDTO;

import java.util.List;

/**
 * <p>
 * 商品类目 服务类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
public interface ICategoryService extends IService<Category> {

    /**
     * 查询所有的类别
     * @return
     */
    List<CategoryDTO> listAll();
    /**
     * 删除多个购物车
     * @param ids
     */


}
