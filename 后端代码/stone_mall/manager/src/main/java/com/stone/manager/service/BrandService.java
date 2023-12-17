package com.stone.manager.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.product.CategoryBrandDto;
import com.stone.model.entity.product.Brand;
import com.stone.model.entity.product.CategoryBrand;

import java.util.List;

public interface BrandService {
    PageInfo<Brand> list(Integer current, Integer size);

    void save(Brand brand);

    void update(Brand brand);

    void removeById(Long id);

    List<Brand> findAll();

    PageInfo<CategoryBrand> findByPage(Integer current, Integer size, CategoryBrandDto categoryBrandDto);

    void saveCategoryBrand(CategoryBrand categoryBrand);

    List<Brand> findBrandByCategoryId(Long categoryId);
}
