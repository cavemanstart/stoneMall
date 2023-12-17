package com.stone.manager.controller;

import com.github.pagehelper.PageInfo;
import com.stone.manager.service.BrandService;
import com.stone.manager.service.CategoryService;
import com.stone.model.dto.product.CategoryBrandDto;
import com.stone.model.entity.product.Brand;
import com.stone.model.entity.product.CategoryBrand;
import com.stone.model.vo.common.Result;
import org.apache.logging.log4j.message.ReusableMessage;
import org.checkerframework.checker.units.qual.Current;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Currency;
import java.util.List;

@RestController
@RequestMapping("admin/product/categoryBrand")
public class CategoryBrandController {
    @Autowired
    private BrandService brandService;
    //分类品牌条件分页查询
    @GetMapping("/{current}/{size}")
    public Result findByPage(@PathVariable Integer current, @PathVariable Integer size,
                             CategoryBrandDto categoryBrandDto){
        PageInfo<CategoryBrand> info =  brandService.findByPage(current,size,categoryBrandDto);
        return Result.ok(info);
    }
    //分类品牌的添加功能
    @PostMapping("save")
    public Result save(@RequestBody CategoryBrand categoryBrand){
        brandService.saveCategoryBrand(categoryBrand);
        return Result.ok(null);
    }
    @GetMapping("findBrandByCategoryId/{categoryId}")
    public Result findBrandByCategoryId(@PathVariable Long categoryId){
        List<Brand> list =  brandService.findBrandByCategoryId(categoryId);
        return Result.ok(list);
    }
}
