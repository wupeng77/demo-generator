<#include "/java_copyright.include">
<#include "/macro.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>   
package ${basepackage}.dao;

import java.util.List;
import ${basepackage}.dto.custom.${className}Dto;
import tk.mybatis.mapper.common.Mapper;

public interface ${className}Dao extends Mapper<${className}Dto> {

    public List<${className}Dto> findPageInfo(${className}Dto ${classNameLower}Dto);

}