package com.stone.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.model.dto.h5.ProductSkuDto;
import com.stone.model.dto.product.SkuSaleDto;
import com.stone.model.entity.product.Product;
import com.stone.model.entity.product.ProductDetails;
import com.stone.model.entity.product.ProductSku;
import com.stone.model.vo.h5.ProductItemVo;
import com.stone.product.mapper.ProductMapper;
import com.stone.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<ProductSku> findProductSkuBySale() {
        return productMapper.findProductSkuBySale();
    }

    @Override
    public PageInfo<ProductSku> findByPage(Integer page, Integer limit, ProductSkuDto productSkuDto) {
        PageHelper.startPage(page,limit);
        List<ProductSku> list =  productMapper.findByKeyWord(productSkuDto);
        return new PageInfo<>(list);
    }

    @Override
    public ProductItemVo item(Long skuId) {
        ProductSku productSku = productMapper.getSkuBySkuId(skuId);
        Product product = productMapper.getProductById(productSku.getProductId());
        List<ProductSku> skuList =  productMapper.productSkuList(productSku.getProductId());
        Map<String, Object> skuSpecValueMap = new HashMap<>();
        skuList.forEach(sku ->{
            skuSpecValueMap.put(sku.getSkuSpec(),sku.getId());
        });
        ProductDetails productDetails = productMapper.getDetailsById(productSku.getProductId());
        ProductItemVo productItemVo = new ProductItemVo();
        productItemVo.setProductSku(productSku);
        productItemVo.setProduct(product);
        productItemVo.setDetailsImageUrlList(Arrays.asList(productDetails.getImageUrls().split(",")));
        productItemVo.setSpecValueList(JSON.parseArray(product.getSpecValue()));
        productItemVo.setSliderUrlList(Arrays.asList(product.getSliderUrls().split(",")));
        productItemVo.setSkuSpecValueMap(skuSpecValueMap);
        return productItemVo;
    }

    @Override
    public ProductSku getSkuBySkuId(Long skuId) {
        return productMapper.getSkuBySkuId(skuId);
    }

    @Override
    @Transactional
    public Boolean updateSkuSaleNum(List<SkuSaleDto> skuSaleDtoList) {

        if(!CollectionUtils.isEmpty(skuSaleDtoList)) {
            for(SkuSaleDto skuSaleDto : skuSaleDtoList) {
                productMapper.updateSale(skuSaleDto.getSkuId(), skuSaleDto.getNum());
            }
        }
        return true;
    }
}
