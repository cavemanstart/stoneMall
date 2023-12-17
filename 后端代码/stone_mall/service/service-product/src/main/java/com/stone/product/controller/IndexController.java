package com.stone.product.controller;
import com.stone.model.entity.product.Category;
import com.stone.model.entity.product.ProductSku;
import com.stone.model.vo.common.Result;
import com.stone.model.vo.h5.IndexVo;
import com.stone.product.service.CategoryService;
import com.stone.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Tag(name = "首页接口管理")
@RestController
@RequestMapping("api/product/index")
@SuppressWarnings({"unchecked", "rawtypes"})
//@CrossOrigin
public class IndexController {

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductService productService;

    @Operation(summary = "获取首页数据")
    @GetMapping
    public Result findData(){
        List<Category> categoryList = categoryService.findOneCategory();
        List<ProductSku> productSkuList =productService.findProductSkuBySale();
        IndexVo indexVo = new IndexVo();
        indexVo.setCategoryList(categoryList);
        indexVo.setProductSkuList(productSkuList);
        return Result.ok(indexVo);
    }
}
