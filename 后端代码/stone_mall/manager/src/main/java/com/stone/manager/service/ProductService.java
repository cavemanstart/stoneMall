package com.stone.manager.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.product.ProductDto;
import com.stone.model.entity.base.ProductUnit;
import com.stone.model.entity.product.Product;
import com.stone.model.entity.product.ProductSku;

import java.util.List;

public interface ProductService {
    PageInfo<Product> list(Integer pageIndex, Integer pageSize, ProductDto productDto);

    List<ProductUnit> findAllProductUnit();

    void save(Product product);

    Product getById(Long id);

    void update(Product product);

    void deleteById(Long id);

    void updateAuditStatus(Long id, Long auditStatus);

    void updateStatus(Long id, Integer status);

}
