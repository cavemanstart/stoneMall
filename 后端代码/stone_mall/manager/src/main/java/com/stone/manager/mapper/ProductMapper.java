package com.stone.manager.mapper;

import com.stone.model.dto.product.ProductDto;
import com.stone.model.entity.base.ProductUnit;
import com.stone.model.entity.product.Product;
import com.stone.model.entity.product.ProductDetails;
import com.stone.model.entity.product.ProductSku;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {
    List<Product> list(ProductDto productDto);

    List<ProductUnit> findAllProductUnit();

    void save(Product product);

    void saveSku(ProductSku productSku);

    void saveDetails(ProductDetails productDetails);

    Product getProductById(Long id);

    List<ProductSku> getProductSkuList(Long id);

    ProductDetails getProductDetails(Long id);

    void update(Product product);

    void updateProductSkuById(ProductSku productSku);

    void updateProductDetails(ProductDetails productDetails);

    void deleteById(Long id);

    void deleteSkuByProductId(Long id);

    void deleteDetailsByProductId(Long id);
}
