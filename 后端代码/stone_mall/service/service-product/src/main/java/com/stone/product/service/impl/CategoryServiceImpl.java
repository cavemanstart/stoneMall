package com.stone.product.service.impl;

import com.alibaba.fastjson.JSON;
import com.stone.model.entity.product.Category;
import com.stone.product.mapper.CategoryMapper;
import com.stone.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Override
    public List<Category> findOneCategory() {
        String categoryListJson = redisTemplate.opsForValue().get("category:one");
        if(StringUtils.hasText(categoryListJson)){//redis里面有直接查询获取，返回
            return JSON.parseArray(categoryListJson, Category.class);
        }
        List<Category> oneCategory = categoryMapper.findOneCategory();
        redisTemplate.opsForValue().set("category:one",JSON.toJSONString(oneCategory),7, TimeUnit.DAYS);
        return oneCategory;
    }

    @Cacheable(value = "category",key = "'tree'")
    @Override
    public List<Category> findCategoryTree() {
        //String categoryListJson = redisTemplate.opsForValue().get("category:tree");
        //if(StringUtils.hasText(categoryListJson)){//redis里面有直接查询获取，返回
        //    return JSON.parseArray(categoryListJson, Category.class);
        //}
        List<Category> allList = categoryMapper.findAll();
        List<Category> oneList = allList.stream().filter(item -> item.getParentId()==0)
                .collect(Collectors.toList());
        if(!CollectionUtils.isEmpty(oneList)){
            oneList.forEach(one -> {
                List<Category> twoList =allList.stream().filter(it->it.getParentId().equals(one.getId())).collect(Collectors.toList());

                twoList.forEach( two ->{
                    List<Category> threeList = allList.stream().filter(it-> it.getParentId().equals(two.getId()))
                            .collect(Collectors.toList());
                    two.setChildren(threeList);
                });
                one.setChildren(twoList);
            });
        }
        //redisTemplate.opsForValue().set("category:tree",JSON.toJSONString(oneList),30,TimeUnit.MINUTES);
        return oneList;
    }
}
