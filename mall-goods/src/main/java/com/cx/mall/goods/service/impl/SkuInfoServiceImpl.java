package com.cx.mall.goods.service.impl;

import com.baomidou.mybatisplus.extension.conditions.update.LambdaUpdateChainWrapper;
import com.cx.mall.goods.entity.SkuInfo;
import com.cx.mall.goods.mapper.SkuInfoMapper;
import com.cx.mall.goods.model.Stock;
import com.cx.mall.goods.service.ISkuInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@Service
public class SkuInfoServiceImpl extends ServiceImpl<SkuInfoMapper, SkuInfo> implements ISkuInfoService {
    @Autowired
    private SkuInfoMapper baseMapper;

    public void dcount(List<Stock> stocks) throws Exception {
        /*
         * 遍历购物车数据
         * 如果找到库存商品，计算库存剩余量
         * 如果库存剩余量>=0，修改库存，否则抛出异常
         */
        for (Stock stock : stocks) {
            SkuInfo skuInfo = baseMapper.selectById(stock.getSkuId());
            if(skuInfo != null){
                int margin =  skuInfo.getNum() - stock.getNum();
                if(margin >= 0){
                    // 条件构造器
                    LambdaUpdateChainWrapper<SkuInfo> updateChainWrapper = new LambdaUpdateChainWrapper<>(baseMapper);
                    // 只修改num字段的值
                    updateChainWrapper.eq(SkuInfo::getId,skuInfo.getId())
                            .set(SkuInfo::getNum,margin).update();
                }else {
                    throw  new Exception("库存不足");
                }
            }
        }
    }

}
