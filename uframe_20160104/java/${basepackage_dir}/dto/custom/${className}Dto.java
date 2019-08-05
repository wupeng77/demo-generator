<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
package ${basepackage}.dto.custom;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;
import ${basepackage}.dto.domain.${className}DtoBase;

/**
 * ${table.tableAlias}
 */
@Table(name = "${table.sqlName}")
public class ${className}Dto extends ${className}DtoBase implements Serializable {

    private static final long serialVersionUID = ${className}Dto.class.getName().hashCode();
}