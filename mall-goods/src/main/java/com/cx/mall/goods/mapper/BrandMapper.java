package com.cx.mall.goods.mapper;

import com.cx.mall.goods.entity.Brand;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 品牌表 Mapper 接口
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */

public interface BrandMapper extends BaseMapper<Brand> {
    /**
     * 按一级分类ID 查询品牌
     * @param id
     * @return
     */
    List<Brand> selectByOneCategoryId(int id);

    /**
     * 按二级分类ID 查询品牌
     * @param id
     * @return
     */
    List<Brand> selectByTwoCategoryId(int id);
    /**
     * 按三级级分类 ID 查询品牌
     * @param id
     * @return
     */
    List<Brand> selectByThreeCategoryId(int id);

}

