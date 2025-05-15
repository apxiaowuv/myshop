package com.cx.mall.goods.service;

import com.cx.mall.goods.entity.Brand;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 品牌表 服务类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
public interface IBrandService extends IService<Brand> {
    /**
     * 按一级分类 ID 查询
     * @param id
     * @return
     */
    List<Brand> listByOneCategoryId(int id);
    /**
     * 按三级分类 ID 查询
     * @param id
     * @return
     */
    List<Brand> listByTwoCategoryId(int id);
    /**
     * 按二级分类 ID 查询
     * @param id
     * @return
     */
    List<Brand> listByThreeCategoryId(int id);
}
