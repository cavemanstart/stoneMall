package com.stone.manager.controller;

import cn.hutool.http.server.HttpServerResponse;
import com.stone.manager.service.CategoryService;
import com.stone.model.entity.product.Category;
import com.stone.model.vo.common.Result;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("admin/product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    //分类页面获取
    @GetMapping("findCategoryList/{id}")
    public Result findCategoryList(@PathVariable Long id){
        List<Category> list =  categoryService.findCategoryList(id);
        return Result.ok(list);
    }
    //导出
    @GetMapping("exportData")
    public void exportData(HttpServletResponse response){
        categoryService.exportData(response);
    }
    //导入
    @PostMapping("importData")
    public Result importData(MultipartFile file){
        boolean res = categoryService.importData(file);
        return res?Result.ok(null):Result.build(null,476,"导入失败");
    }

}
