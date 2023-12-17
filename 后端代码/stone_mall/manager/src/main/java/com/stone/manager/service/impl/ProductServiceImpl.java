package com.stone.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.stone.manager.mapper.ProductMapper;
import com.stone.manager.service.ProductService;
import com.stone.model.dto.product.ProductDto;
import com.stone.model.entity.base.ProductUnit;
import com.stone.model.entity.product.Product;
import com.stone.model.entity.product.ProductDetails;
import com.stone.model.entity.product.ProductSku;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.sampled.Port;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public PageInfo<Product> list(Integer pageIndex, Integer pageSize, ProductDto productDto) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Product> list =  productMapper.list(productDto);
        return new PageInfo<>(list);
    }

    @Override
    public List<ProductUnit> findAllProductUnit() {

        return productMapper.findAllProductUnit();
    }

    @Override
    public void save(Product product) {
        //保存商品基本信息
        product.setStatus(0);
        product.setAuditStatus(0);
        productMapper.save(product);
        //获取商品sku列表
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(int i=0; i<productSkuList.size(); ++i){
            ProductSku productSku = productSkuList.get(i);
            productSku.setSkuCode(product.getId()+"_"+i);
            productSku.setProductId(product.getId());
            productSku.setSkuName(product.getName()+productSku.getSkuSpec());
            productSku.setStatus(0);
            productSku.setSaleNum(0);
            productMapper.saveSku(productSku);
        }
        //保存商品详情数据
        ProductDetails productDetails = new ProductDetails();
        productDetails.setProductId(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productMapper.saveDetails(productDetails);
    }

    @Override
    public Product getById(Long id) {
        //根据id查询商品信息
        Product product = productMapper.getProductById(id);
        //查询 productSku列表
        List<ProductSku> productSkuList = productMapper.getProductSkuList(id);
        product.setProductSkuList(productSkuList);
        //查询 product_details
        ProductDetails productDetails = productMapper.getProductDetails(id);
        product.setDetailsImageUrls(productDetails.getImageUrls());
        return product;
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
        List<ProductSku> productSkuList = product.getProductSkuList();
        for(ProductSku productSku: productSkuList){
            productMapper.updateProductSkuById(productSku);
        }
        ProductDetails productDetails = productMapper.getProductDetails(product.getId());
        productDetails.setImageUrls(product.getDetailsImageUrls());
        productMapper.updateProductDetails(productDetails);
    }

    @Override
    public void deleteById(Long id) {
        //根据id删除product表
        productMapper.deleteById(id);
        //根据product_id删除product_sku
        productMapper.deleteSkuByProductId(id);
        //根据product_id删除product_details
        productMapper.deleteDetailsByProductId(id);
    }

    @Override
    public void updateAuditStatus(Long id, Long auditStatus) {
        Product product = new Product();
        product.setId(id);
        if(auditStatus==1){
            product.setAuditStatus(1);
            product.setAuditMessage("审批通过");
        }else if(auditStatus==-1){
            product.setAuditStatus(-1);
            product.setAuditMessage("审批不通过");
        }
        productMapper.update(product);
    }

    @Override
    public void updateStatus(Long id, Integer status) {
        Product product = new Product();
        product.setId(id);
        if(status==1){
            product.setStatus(1);
        }else product.setStatus(-1);
        productMapper.update(product);
    }
}
