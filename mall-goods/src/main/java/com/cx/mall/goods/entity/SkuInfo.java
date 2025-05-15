package com.cx.mall.goods.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author ke
 * @since 2025-04-17
 */
@Data
@Getter
@Setter
@TableName("sku_info")
public class SkuInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
      @TableId(value = "id",type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * SPUID
     */
    @TableField("spu_id")
    private Long spuId;

    /**
     * 价格
     */
    @TableField("price")
    private Long price;

    /**
     * sku名称
     */
    @TableField("sku_name")
    private String skuName;

    /**
     * 商品规格描述
     */
    @TableField("sku_attribute")
    private String skuAttribute;

    /**
     * 数量
     */
    @TableField("num")
    private Integer num;

    /**
     * 品牌
     */
    @TableField("brand_id")
    private Long brandId;

    /**
     * 品牌名称
     */
    @TableField("brand_name")
    private String brandName;

    /**
     * 分类id
     */
    @TableField("category_id")
    private Long categoryId;

    /**
     * 分类名称
     */
    @TableField("category_name")
    private String categoryName;

    /**
     * 商品图片
     */
    @TableField("sku_default_img")
    private String skuDefaultImg;

    /**
     * 商品图片列表
     */
    @TableField("images")
    private String images;

    /**
     * 商品状态（1：已下架 0：正常）
     */
    @TableField(value = "status",fill = FieldFill.INSERT)
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 商品状态（1：已删除 0：正常）
     */
    @TableField("deleted")
    private Integer deleted;
}
