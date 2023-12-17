package com.stone.manager.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.read.listener.ReadListener;
import com.stone.manager.mapper.CategoryMapper;
import com.stone.model.vo.product.CategoryExcelVo;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener<T> implements ReadListener<T> {
    private CategoryMapper categoryMapper;
    private final static Integer CACHE_SIZE = 100;
    private List<T> cacheList = new ArrayList<>(CACHE_SIZE);
    public ExcelListener(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        cacheList.add(t);
        if(cacheList.size()>=CACHE_SIZE){
            batchSave();
            cacheList.clear();
        }
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        batchSave();
    }
    private void batchSave() {
        categoryMapper.batchSave(cacheList);
    }
}
