package com.stone.manager.controller;
import com.github.pagehelper.PageInfo;
import com.stone.manager.service.BrandService;
import com.stone.model.entity.product.Brand;
import com.stone.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;
    //分页列表
    @GetMapping("/{current}/{size}")
    public Result list(@PathVariable Integer current, @PathVariable Integer size){
        PageInfo<Brand> pageInfo = brandService.list(current,size);
        return Result.ok(pageInfo);
    }
    //添加
    @PostMapping("save")
    public Result save(@RequestBody Brand brand){
        brandService.save(brand);
        return Result.ok(null);
    }
    //修改
    @PutMapping("update")
    public Result update(@RequestBody Brand brand){
        brandService.update(brand);
        return Result.ok(null);
    }
    //删除
    @DeleteMapping("remove/{id}")
    public Result remove(@PathVariable Long id){
        brandService.removeById(id);
        return Result.ok(null);
    }
    //查询所有品牌
    @GetMapping("findAll")
    public Result findAll(){
        List<Brand> all = brandService.findAll();
        return Result.ok(all);
    }

}
