package com.stone.product.mapper;

import com.stone.model.entity.product.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findOneCategory();

    List<Category> findAll();
}
