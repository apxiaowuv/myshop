package com.cx.mall.goods.service.impl;

import com.cx.mall.goods.entity.Brand;
import com.cx.mall.goods.mapper.BrandMapper;
import com.cx.mall.goods.service.IBrandService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 品牌表 服务实现类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@Service
public class BrandServiceImpl extends ServiceImpl<BrandMapper, Brand>
        implements IBrandService {
    @Override
    public List<Brand> listByOneCategoryId(int id) {
        return baseMapper.selectByOneCategoryId(id);
    }
    @Override
    public List<Brand> listByTwoCategoryId(int id) {
        return baseMapper.selectByTwoCategoryId(id);
    }
    @Override
    public List<Brand> listByThreeCategoryId(int id) {
        return baseMapper.selectByThreeCategoryId(id);
    }
}

