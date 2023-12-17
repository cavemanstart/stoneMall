package com.stone.manager.controller;

import com.github.pagehelper.PageInfo;
import com.stone.manager.service.ProductSpecService;
import com.stone.model.entity.product.ProductSpec;
import com.stone.model.vo.common.Result;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin/product/productSpec")
public class ProductSpecController {
    @Autowired
    private ProductSpecService productSpecService;
    //列表
    @GetMapping("/{pageIndex}/{pageSize}")
    public Result getPage(@PathVariable Integer pageIndex, @PathVariable Integer pageSize){
        PageInfo<ProductSpec> info =  productSpecService.getPage(pageIndex,pageSize);
        return Result.ok(info);
    }
    //添加
    @PostMapping("save")
    public Result save(@RequestBody ProductSpec productSpec){
        productSpecService.save(productSpec);
        return Result.ok(null);
    }
    //修改
    @PutMapping("updateById")
    public Result updateById(@RequestBody ProductSpec productSpec){
        productSpecService.updateById(productSpec);
        return Result.ok(null);
    }
    //删除
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        productSpecService.deleteById(id);
        return Result.ok(null);
    }
    @GetMapping("findAll")
    public Result findAll(){
        List<ProductSpec> list = productSpecService.findAll();
        return Result.ok(list);
    }
}
