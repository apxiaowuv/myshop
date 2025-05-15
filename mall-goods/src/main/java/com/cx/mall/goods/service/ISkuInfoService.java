package com.cx.mall.goods.service;

import com.cx.mall.goods.entity.SkuInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.cx.mall.goods.model.Stock;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
public interface ISkuInfoService extends IService<SkuInfo> {

    /**
     * 库存递减
     * @param stocks
     * @throws Exception
     */
    void dcount(List<Stock> stocks) throws Exception;

}
