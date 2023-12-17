package com.stone.manager.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.entity.product.ProductSpec;

import java.util.List;

public interface ProductSpecService {
    PageInfo<ProductSpec> getPage(Integer pageIndex, Integer pageSize);

    void save(ProductSpec productSpec);

    void updateById(ProductSpec productSpec);

    void deleteById(Long id);

    List<ProductSpec> findAll();
}
