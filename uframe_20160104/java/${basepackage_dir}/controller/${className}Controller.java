<#include "/java_copyright.include">
        <#include "/macro.include">
        <#assign className = table.className>
        <#assign classNameLower = className?uncap_first>
package ${basepackage}.controller;

import java.util.List;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;

import ${basepackage}.dto.custom.${className}Dto;
import ${basepackage}.service.${className}Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${classNameLower}")
public class ${className}Controller {
    @Autowired
    private ${className}Service ${classNameLower}Service;

    /**
     * 列表
    */
    @RequestMapping(value = "list", method = RequestMethod.POST)
    public PageInfo<${className}Dto> list(@RequestBody ${className}Dto commonDto)
        throws Exception {
        List<${className}Dto> list = ${classNameLower}Service.findPageInfo(commonDto);
        return new PageInfo<${className}Dto>(list);
    }

    /**
     * 主键查询
    */
    @RequestMapping(value = "select", method = RequestMethod.POST)
    public ${className}Dto select(@RequestBody ${className}Dto ${classNameLower}Dto)
        throws Exception {
        ${classNameLower}Dto = ${classNameLower}Service.selectByPrimaryKey(${classNameLower}Dto.get${className}Id());
        return ${classNameLower}Dto;
    }

    /**
    * 查询所有
    */
    @RequestMapping(value = "selectAll", method = RequestMethod.POST)
    public List<${className}Dto> selectAll()
        throws Exception {
        return  ${classNameLower}Service.selectAll();
    }
    /**
     * 删除
    */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public int delete(@RequestBody ${className}Dto ${classNameLower}Dto)
        throws Exception {
        int flag = ${classNameLower}Service.delete(${classNameLower}Dto);
        return flag;
    }

    /**
     * 更新
    */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public int update(@RequestBody ${className}Dto ${classNameLower}Dto)
        throws Exception {
        int flag = ${classNameLower}Service.updateByPrimaryKeySelective(${classNameLower}Dto);
        return flag;
    }

    /**
     * 插入
    */
    @RequestMapping(value = "insert", method = RequestMethod.POST)
    public int insert(@RequestBody ${className}Dto ${classNameLower}Dto)
        throws Exception {
        int flag = ${classNameLower}Service.insert(${classNameLower}Dto);
        return flag;
    }

    /**
     * 批量删除
    */
    @RequestMapping(value = "deleteList", method = RequestMethod.POST)
    public int deleteList(@RequestBody List<${className}Dto> ${classNameLower}DtoList)
        throws Exception {
        int flag = ${classNameLower}Service.deleteList(${classNameLower}DtoList);
        return flag;
    }

    /**
    * 批量删除
    */
    @RequestMapping(value = "insertAll", method = RequestMethod.POST)
    public int insertAll(@RequestBody List<${className}Dto> ${classNameLower}DtoList)
        throws Exception {
        int flag = ${classNameLower}Service.insertAll(${classNameLower}DtoList);
        return flag;
    }
}