package com.stone.product.service;

import com.github.pagehelper.PageInfo;
import com.stone.model.dto.h5.ProductSkuDto;
import com.stone.model.dto.product.SkuSaleDto;
import com.stone.model.entity.product.ProductSku;
import com.stone.model.vo.h5.ProductItemVo;

import java.util.List;

public interface ProductService {
    List<ProductSku> findProductSkuBySale();

    PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto);

    ProductItemVo item(Long skuId);

    ProductSku getSkuBySkuId(Long skuId);

    Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList);
}
