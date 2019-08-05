<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.service;

import com.github.pagehelper.PageInfo;
import ${basepackage}.dto.custom.${className}Dto;
import ${basepackage}.dao.${className}Dao;
import java.util.List;

public interface ${className}Service extends ${className}Dao {

    public PageInfo<${className}Dto> findPageInfo(int page, int rows, ${className}Dto ${classNameLower}Dto, String orderBy);

    public int deleteList(List<${className}Dto> ${classNameLower}DtoList) throws Exception;

    public int insertAll(List<${className}Dto> ${classNameLower}DtoList) throws Exception;
}