package com.stone.manager.service.impl;

import com.alibaba.excel.EasyExcel;
import com.stone.exception.StoneException;
import com.stone.manager.listener.ExcelListener;
import com.stone.manager.mapper.CategoryMapper;
import com.stone.manager.service.CategoryService;
import com.stone.model.entity.product.Category;
import com.stone.model.vo.product.CategoryExcelVo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public List<Category> findCategoryList(Long id) {
        List<Category> list =  categoryMapper.findCategoryList(id);
        list.forEach(category -> {
            int cnt = categoryMapper.hasChildren(category.getId());
            if(cnt>0)
                category.setHasChildren(true);
            else category.setHasChildren(false);
        });
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try{
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String filename = "categories-excel";
            response.setHeader("Content-disposition","attachment;filename="+filename+".xlsx");
            List<Category> list =  categoryMapper.findAll();
            List<CategoryExcelVo> voList = new ArrayList<>();
            for(Category category : list){
                CategoryExcelVo excelVo = new CategoryExcelVo();
                BeanUtils.copyProperties(category,excelVo);
                voList.add(excelVo);
            }
            EasyExcel.write(response.getOutputStream(),CategoryExcelVo.class).sheet().doWrite(voList);
        }catch (Exception e){
            e.printStackTrace();
            throw new StoneException(579,"导出excel失败");
        }
    }

    @Override
    public boolean importData(MultipartFile file) {
        try {
            ExcelListener<CategoryExcelVo> excelListener = new ExcelListener(categoryMapper);
            EasyExcel.read(file.getInputStream(),CategoryExcelVo.class,excelListener).sheet().doRead();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
