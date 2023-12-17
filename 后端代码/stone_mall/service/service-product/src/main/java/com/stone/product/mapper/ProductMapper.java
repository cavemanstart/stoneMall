package com.stone.product.mapper;

import com.stone.model.dto.h5.ProductSkuDto;
import com.stone.model.entity.product.Product;
import com.stone.model.entity.product.ProductDetails;
import com.stone.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<ProductSku> findProductSkuBySale();

    List<ProductSku> findByKeyWord(ProductSkuDto productSkuDto);

    ProductSku getSkuBySkuId(Long skuId);

    Product getProductById(Long productId);

    List<ProductSku> productSkuList(Long productId);

    ProductDetails getDetailsById(Long productId);

    void updateSale(Long skuId, Integer num);
}
