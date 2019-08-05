<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dto.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * ${table.tableAlias}
 */
public class ${className}DtoBase  implements Serializable {

    private static final long serialVersionUID = ${className}DtoBase.class.getName().hashCode();

    <@generateFieldsNew/>
    <@generateProperties/>

    <#macro generateFieldsNew>
    <#--自定义函数，根据数据库中表字段生成java中的属性 -->
    <#list table.columns as column>
    /** ${column.columnAlias!} */
    <#if column.isDateTimeColumn>
    <#if (column.columnNameLower != 'createdDate')&&(column.columnNameLower != 'updatedDate')>
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    <#elseif (column.columnNameLower == 'createdDate')>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    <#elseif (column.columnNameLower == 'updatedDate')>
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    <#else>
    </#if>
    private ${column.javaType} ${column.columnNameLower};

    <#else>
    <#if column.pk>
    @Id
    </#if>
    private ${column.javaType} ${column.columnNameLower};

    </#if>     
    </#list>
    </#macro>


    <#macro generateProperties>
    <#list table.columns as column>

    /**
     * 获取属性${column.columnAlias!}的值
     */
    public ${column.javaType} get${column.columnName}() {
        return this.${column.columnNameLower};
    }

    /**
     * 设置属性${column.columnAlias!}的值
     */
    public void set${column.columnName}(${column.javaType} ${column.columnNameLower}) {
        this.${column.columnNameLower} = ${column.columnNameLower};
    }
    </#list>
    </#macro>
}