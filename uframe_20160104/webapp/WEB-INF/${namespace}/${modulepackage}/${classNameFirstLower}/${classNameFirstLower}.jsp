<#assign className = table.className> 
<#assign classNameLower =className?uncap_first>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<%@ include file="/WEB-INF/jsp/head.jsp"%>
</head>
<body>
    <div class="container">
        <div class="row">
        	<div class="col-md-12">
        		<div class="widget">
        			<div class="widget-header">
        				<div class="form-group">
        					<div class="pull-left">
        						<s:message code='${className}Dto.queryTitle'/>
        					</div>
        				</div>
        			</div>
					<div class="widget-content">
        				<form class="form-horizontal" method="post" role="form" id="queryConditionForm" action="">
        					<table class="table-query">
        					<#list table.columns as column>
							   <#if (column_index==0)>
							    <tr>
							   	    <td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
							      <#elseif ((column_index%3)!=0)&&(column_has_next)>
							      	<td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
							      <#elseif ((column_index%3)==0)&&(column_has_next)>
							    </tr>
							    <tr>
							        <td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
							      <#elseif ((column_index%3)==0)&&(!column_has_next)>
							    </tr>
							    <tr>
							        <td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
							    </tr>
							      <#elseif ((column_index%3)!=0)&&(!column_has_next)&&((column_index%3)==1)>
							        <td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
						        </tr> 
							      <#elseif ((column_index%3)!=0)&&(!column_has_next)&&((column_index%3)==2)>
							   	    <td class="title"><label><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        							<td>
        								<input type="text" id="search${column.columnName}" name="search${column.columnName}"
        									class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        							</td>
							    </tr>
							   <#else>
							   </#if>
							</#list>
        					</table>
        					<div class="table-query-action">
        						<button type="button" id="btnQuery" class="btn btn-primary"><i class="fa fa-search"></i><s:message code="common.button.query" /></button>
        						<button type="reset" class="btn btn-primary"><i class="fa fa-retweet"></i><s:message code="common.button.reset" /></button>
        					</div>
        				</form>
        			</div>
        		</div>
        		<table id="${classNameLower}Table"></table>
 				<div id="${classNameLower}GridPager"></div>
        	</div>
        </div>
    </div>

	<!-- 管理窗口 -->
    <div id="manageModal" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
 	<div class="modal-dialog">
 		<div class="modal-content">
 			<div class="modal-header">
 		   	<button type="button" class="close" data-dismiss="modal" aria-hidden="true" onclick = "closeModal();">&times;</button>
 				<h4 class="modal-title" id="myModalLabel"><i class="fa fa-pencil-square-o"><s:message code="${className}Dto.manage" /></i></h4>
 		    </div>
 		   <div class="modal-body">
 		   <form id="${classNameLower}Form">
 		   		<input type="hidden" id="actionType" name="actionType"/>
 				<table class="table-query">
        		 <#list table.columns as column>
				    <#if (column_index==0)>
				    <tr>
				    	<td class="title"><label class="required"><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        		 		<td>
        		 			<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"
        		 				class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        		 		</td>
				       <#elseif ((column_index%2)!=0)&&(column_has_next)>
				       	<td class="title"><label class="required"><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        		 		<td>
        		 			<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"
        		 				class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        		 		</td>
				       <#elseif ((column_index%2)==0)&&(column_has_next)>
				    </tr>
				    <tr>
				        <td class="title"><label class="required"><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        		 		<td>
        		 			<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"
        		 				class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        		 		</td>
				       <#elseif ((column_index%2)==0)&&(!column_has_next)>
				    </tr>
				    <tr>
				        <td class="title"><label class="required"><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        		 		<td>
        		 			<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"
        		 				class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        		 		</td>
				    </tr>
				       <#elseif ((column_index%2)!=0)&&(!column_has_next)&&((column_index%2)==1)>
				        <td class="title"><label class="required"><s:message code="${className}Dto.${column.columnNameLower}" /> :</label></td>
        		 		<td>
        		 			<input type="text" id="${column.columnNameLower}" name="${column.columnNameLower}"
        		 				class="form-control" placeholder=" <s:message code='commom.input.placeholder' />" />
        		 		</td>
				    </tr>
				    <#else>
				    </#if>
				 </#list>
        		 </table>
 			</form>
        	</div>
        	<div class="modal-footer">
 				<button type="button" id="btnSave" class="btn btn-primary"><s:message code="common.button.save"/></button>
 				<button type="button" class="btn btn-default" data-dismiss="modal" onclick = "closeModal();"><s:message code="common.button.close"/></button>
 			</div>
         </div>
      </div>
    </div>
    <%@include file="/WEB-INF/jsp/common/alertmodal.jsp" %>
    <%@include file="/WEB-INF/jsp/common/message.jsp" %>
    
    <script type="text/javascript">
    <#list table.columns as column>
    var ${column.columnNameLower}Desc='<s:message code="${className}Dto.${column.columnNameLower}" />';
    </#list>
    </script>
    <script language="Javascript" src="${r'${'}ctx${r'}'}/js/${modulepackage}/${classNameLower}/${classNameLower}.js" ></script>
</body>
</html>