<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import ${commonspackage}.service.impl.IBaseServiceImpl;

import ${basepackage}.dao.${className}Dao;
import ${basepackage}.dto.custom.${className}Dto;
import ${basepackage}.service.${className}Service;

@Service("${classNameLower}Service")
public class ${className}ServiceImpl extends IBaseServiceImpl<${className}Dto> implements ${className}Service {

    @Autowired
    private ${className}Dao ${classNameLower}Dao;

    @Override
    public PageInfo<${className}Dto> findPageInfo(int page, int rows, ${className}Dto ${classNameLower}Dto, String orderBy) {
        PageHelper.startPage(page, rows, orderBy);
        List<${className}Dto> list = ${classNameLower}Dao.findPageInfo(${classNameLower}Dto);
        return new PageInfo<${className}Dto>(list);
    }

    @Override
    public List<${className}Dto> findPageInfo(${className}Dto ${classNameLower}Dto) {
        List<${className}Dto> list =  ${classNameLower}Dao.select(${classNameLower}Dto);
        return list;
    }

    public ${className}Dto selectByPrimaryKey(Object o) {
        return ${classNameLower}Dao.selectByPrimaryKey(o);
    }

    public List<${className}Dto> selectAll(){
        return ${classNameLower}Dao.selectAll();
    }

    public int delete(${className}Dto ${classNameLower}Dto) {
        return ${classNameLower}Dao.delete(${classNameLower}Dto);
    }

    public int insert(${className}Dto ${classNameLower}Dto) {
        return ${classNameLower}Dao.insert(${classNameLower}Dto);
    }

    public int updateByPrimaryKeySelective(${className}Dto ${classNameLower}Dto) {
        return ${classNameLower}Dao.updateByPrimaryKeySelective(${classNameLower}Dto);
    }

    public int deleteList(List<${className}Dto> ${classNameLower}DtoList) throws Exception{
        int flag = 0;
        try{
             for(${className}Dto ${classNameLower}Dto : ${classNameLower}DtoList){
                 flag = ${classNameLower}Dao.delete(${classNameLower}Dto);
             }
        }catch (Exception e){
             e.printStackTrace();;
        }
        return flag;
    }

    public int insertAll(List<${className}Dto> ${classNameLower}DtoList) throws Exception {
        int flag = 0;
        try {
            for (${className}Dto ${classNameLower}Dto : ${classNameLower}DtoList) {
               flag = ${classNameLower}Dao.insert(${classNameLower}Dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
}