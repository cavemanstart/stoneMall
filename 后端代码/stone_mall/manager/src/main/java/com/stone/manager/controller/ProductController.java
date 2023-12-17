package com.stone.manager.controller;
import com.github.pagehelper.PageInfo;
import com.stone.manager.service.ProductService;
import com.stone.model.dto.product.ProductDto;
import com.stone.model.entity.product.Product;
import com.stone.model.vo.common.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin/product/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    //分页列表
    @GetMapping("/{pageIndex}/{pageSize}")
    public Result list(@PathVariable Integer pageIndex, @PathVariable Integer pageSize, ProductDto productDto){
        PageInfo<Product> info =  productService.list(pageIndex,pageSize,productDto);
        return Result.ok(info);
    }
    //添加
    @PostMapping("save")
    public Result save(@RequestBody Product product){
        productService.save(product);
        return Result.ok(null);
    }
    //根据商品id查询商品信息
    @GetMapping("getById/{id}")
    public Result getPrudoctById(@PathVariable Long id){
        Product product = productService.getById(id);
        return Result.ok(product);
    }
    //修改商品
    @PutMapping("updateById")
    public Result update(@RequestBody Product product){
        productService.update(product);
        return Result.ok(null);
    }
    //删除
    @DeleteMapping("deleteById/{id}")
    public Result deleteById(@PathVariable Long id){
        productService.deleteById(id);
        return Result.ok(null);
    }
    //审核
    @GetMapping("updateAuditStatus/{id}/{auditStatus}")
    public Result updateAuditStatus(@PathVariable Long id, @PathVariable Long auditStatus){
        productService.updateAuditStatus(id,auditStatus);
        return Result.ok(null);
    }
    //更改状态
    @GetMapping("updateStatus/{id}/{status}")
    public Result updateStatus(@PathVariable Long id, @PathVariable Integer status){
        productService.updateStatus(id, status);
        return Result.ok(null);
    }
}
