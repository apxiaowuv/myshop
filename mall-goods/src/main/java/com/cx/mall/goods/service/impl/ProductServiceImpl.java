package com.cx.mall.goods.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cx.mall.goods.entity.Category;
import com.cx.mall.goods.entity.SkuInfo;
import com.cx.mall.goods.mapper.CategoryMapper;
import com.cx.mall.goods.mapper.SkuInfoMapper;
import com.cx.mall.goods.model.Product;
import com.cx.mall.goods.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements IProductService {
    @Autowired // 注入分类 DAO
    private CategoryMapper categoryMapper;
    @Autowired // 注入商品 DAO
    private SkuInfoMapper skuInfoMapper;
    @Override
    public IPage page(Product product) {
        // 商品查询条件构建器
        LambdaQueryChainWrapper<SkuInfo> skuWrapper = new
                LambdaQueryChainWrapper<>(skuInfoMapper);
        /*
         * 按商品类别查询
         * 如果三级类别不为空，按三级类别查询
         * 否则，如果二级类别不为空，按二级类别查询
         * 否则，如果一级类别不为空，按一级类别查询
         */
        if(product.getCategoryId() != null){
            skuWrapper.eq(SkuInfo::getCategoryId,product.getCategoryId());
        }else if(product.getTwoCategoryId() != null){
            // 1 查询二级类别下的所有三级类别
            // 分类查询条件构建器
            LambdaQueryChainWrapper<Category> categoryWrapper = new
                    LambdaQueryChainWrapper<>(categoryMapper);
            List<Category> list = categoryWrapper.eq(Category::getParentId,
                            product.getTwoCategoryId())
                    .select(Category::getId) // 选取指定列
                    .list();
            // 提取类别 ID
            List<Integer> ids =
                    list.stream().map(Category::getId).collect(Collectors.toList());
            // 2 构建查询条件
            skuWrapper.in(SkuInfo::getCategoryId,ids);
        }else if(product.getOneCategoryId() != null){
            // 1 查询一级类别下的所有二级类别
            // 分类查询条件构建器
            LambdaQueryChainWrapper<Category> oneWrapper = new
                    LambdaQueryChainWrapper<>(categoryMapper);
            List<Category> ones = oneWrapper.eq(Category::getParentId,
                            product.getOneCategoryId())
                    .select(Category::getId).list();
            // 提取类别 ID
            List<Integer> oneIds =
                    ones.stream().map(Category::getId).collect(Collectors.toList());
            // 2 查询二级类别下的所有三级类别
            // 重建查询条件构造器
            LambdaQueryChainWrapper<Category> twoWrapper = new
                    LambdaQueryChainWrapper<>(categoryMapper);
            List<Category> list = twoWrapper.in(Category::getParentId, oneIds)
                    .select(Category::getId).list();
            // 提取类别 ID
            List<Integer> ids =
                    list.stream().map(Category::getId).collect(Collectors.toList());
            // 3 构建查询条件
            skuWrapper.in(SkuInfo::getCategoryId,ids);
        }
        // 按品牌查询
        if(product.getBrandId() != null){
            skuWrapper.eq(SkuInfo::getBrandId,product.getBrandId());
        }
        // 按商品名称查询
        if(product.getSkuName() != null){
            skuWrapper.like(SkuInfo::getSkuName,product.getSkuName());
        }
        // 封装 Page
        Page page = new Page();
        if(product.getCurrent() != null && product.getSize() != null){
            page = new Page(product.getCurrent(),product.getSize());
        }
        // 选取列

        skuWrapper.select(SkuInfo::getId,SkuInfo::getSkuName,SkuInfo::getPrice,SkuInfo::getSkuDefaultImg);
        return skuWrapper.page(page);
    }
}

