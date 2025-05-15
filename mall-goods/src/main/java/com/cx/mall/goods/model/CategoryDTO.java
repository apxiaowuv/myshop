package com.cx.mall.goods.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.cx.mall.goods.entity.Category;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class CategoryDTO extends Category {
    private List<CategoryDTO> categoryList;// 每个分类包含多个子分类
}
