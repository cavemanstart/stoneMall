package com.stone.manager.controller;

import com.stone.manager.service.ProductService;
import com.stone.model.entity.base.ProductUnit;
import com.stone.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("admin/product/productUnit")
public class ProductUnitController {
    @Autowired
    private ProductService productService;
    @GetMapping("findAll")
    public Result findAll(){
        List<ProductUnit> list =  productService.findAllProductUnit();
        return Result.ok(list);
    }
}
