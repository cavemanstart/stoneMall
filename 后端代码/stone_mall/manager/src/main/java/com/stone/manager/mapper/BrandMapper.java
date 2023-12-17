package com.stone.manager.mapper;

import com.stone.manager.service.BrandService;
import com.stone.model.dto.product.CategoryBrandDto;
import com.stone.model.entity.product.Brand;
import com.stone.model.entity.product.CategoryBrand;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface BrandMapper {
    List<Brand> findPage();

    void save(Brand brand);

    void update(Brand brand);
    void removeById(Long id);

    List<CategoryBrand> categoryBrandList(CategoryBrandDto categoryBrandDto);

    void saveCategoryBrand(CategoryBrand categoryBrand);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
