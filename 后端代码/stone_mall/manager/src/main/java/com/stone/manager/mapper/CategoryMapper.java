package com.stone.manager.mapper;

import com.stone.model.entity.product.Category;
import com.stone.model.vo.product.CategoryExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    List<Category> findCategoryList(@Param("id") Long id);
    int hasChildren(Long id);

    List<Category> findAll();
    <T> void batchSave(T cacheList);

}
