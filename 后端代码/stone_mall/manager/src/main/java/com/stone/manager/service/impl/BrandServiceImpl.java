package com.stone.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.manager.mapper.BrandMapper;
import com.stone.manager.service.BrandService;
import com.stone.model.dto.product.CategoryBrandDto;
import com.stone.model.entity.product.Brand;
import com.stone.model.entity.product.CategoryBrand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {
    @Autowired
    private BrandMapper brandMapper;
    @Override
    public PageInfo<Brand> list(Integer current, Integer size) {
        PageHelper.startPage(current,size);
        List<Brand> list = brandMapper.findPage();
        PageInfo<Brand> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public void save(Brand brand) {
        brandMapper.save(brand);
    }

    @Override
    public void update(Brand brand) {
        brandMapper.update(brand);
    }

    @Override
    public void removeById(Long id) {
        brandMapper.removeById(id);
    }

    @Override
    public List<Brand> findAll() {
        List<Brand> all =  brandMapper.findPage();
        return all;
    }

    @Override
    public PageInfo<CategoryBrand> findByPage(Integer current, Integer size, CategoryBrandDto categoryBrandDto) {
        PageHelper.startPage(current,size);
        List<CategoryBrand> list = brandMapper.categoryBrandList(categoryBrandDto);
        PageInfo<CategoryBrand> info = new PageInfo<>(list);
        return info;
    }

    @Override
    public void saveCategoryBrand(CategoryBrand categoryBrand) {
        brandMapper.saveCategoryBrand(categoryBrand);
    }

    @Override
    public List<Brand> findBrandByCategoryId(Long categoryId) {
        return  brandMapper.findBrandByCategoryId(categoryId);
    }
}
