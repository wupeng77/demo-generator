<#assign className = table.className> 
<#assign classNameLower =className?uncap_first>
$(document).ready(function() {
    
    validateForm();
    
    initAction(true);
    
    $("#btnQuery").on("click", function () {
        reloadGridTable();
    });
    
    $("#btnSave").on("click", function () {
        deal${className}();
    });
    
    $(function() {
        $(document).scroll(function() {
            $("#${classNameLower}Table").setGridWidth($(window).width()-10);
        });
        $(window).resize(function() {
            $("#${classNameLower}Table").setGridWidth($(window).width()-10);
        });
    });
});

var validator;
function validateForm() {
    validator = $("#${classNameLower}Form").validate({
        focusInvalid : false,
        onkeyup : false,
        rules : {
            <#list table.columns as column>
            ${column.columnNameLower}:{
                required:true,
                byteRangeLength:[0,100]
            }<#if column_has_next>,</#if>
            </#list>            
        }
    });
}

function reloadGridTable() {
    $("#${classNameLower}Table").setGridParam({ 
        postData: {
            <#list table.columns as column>
            "${column.columnNameLower}" : $("#search${column.columnName}").val()<#if column_has_next>,</#if>
            </#list>
        }
    }).trigger("reloadGrid", [{ page: 1}]);
}

var flag = true ;  
function initAction(btnFlag){
    if(flag){
        loadTable();
        if(btnFlag){
            loadTableButtons();
        }
    }
    flag = false;  
}

function showDelFlag(cellvalue, options, rowObject){
    return cellvalue == "N" ? normalDesc:deleteDesc;
}

function loadTable(){
    $("#${classNameLower}Table").jqGrid( {
        url : contextPath + "${classNameLower}/list",
        mtype : 'post',
        datatype : "json",
        ajaxGridOptions : { contentType : 'application/json; charset=utf-8' },
        serializeGridData : function (postData)
        {
            return JSON.stringify(postData);
        },
        colNames : [<#list table.columns as column> <#if (column.columnNameLower=='delFlag')>delFlagDesc<#elseif column.columnNameLower=='createdUser'>createdUserDesc<#elseif column.columnNameLower=='createdDate'>createdDateDesc<#elseif column.columnNameLower=='updatedUser'>updatedUserDesc<#elseif column.columnNameLower=='updatedDate'>updatedDateDesc<#else>${column.columnNameLower}Desc</#if><#if column_has_next>,</#if></#list>],
        colModel : [ 
                    <#list table.columns as column>
                    <#if (column.columnNameLower=='delFlag')>
                     {name : 'delFlag',index : 'delFlag',width : 100,align : "center", formatter: showDelFlag},
                    <#else>
                     {name : '${column.columnNameLower}',index : '${column.columnNameLower}',width : 100,align : "center"}<#if column_has_next>,</#if>
                    </#if>
                    </#list>
                   ],
        rowNum : 10,
        rowList : [ 10, 20, 30, 50 ],
        sortname : 'updatedDate',
        autowidth : true,
        multiboxonly :true,
        multiselectWidth : 30,
        height : 218, //'auto'
        viewrecords : true,
        sortorder : "desc",
        multiselect : true,
        viewrecords : true,
        recordpos : 'right',
        pager : '#${classNameLower}GridPager'
    });
}

function loadTableButtons(){
    $("#${classNameLower}Table")
    .navGrid('#${classNameLower}GridPager',{edit:false,add:false,del:false,search:false,refresh:false})
    .navButtonAdd('#${classNameLower}GridPager',{
        title:addBtnDesc,
        caption:'',
        buttonicon:"fa-plus",
        onClickButton: function(){
            prepareInsert();
        },
        position:"last"
    })
    .navSeparatorAdd("#${classNameLower}GridPager",{sepclass : 'ui-separator',sepcontent: ''})
    .navButtonAdd('#${classNameLower}GridPager',{
        title:editBtnDesc,
        caption:'',
        buttonicon:"fa-edit",
        onClickButton: function(){
            prepareUpdate();
        },
        position:"last"
    })
    .navSeparatorAdd("#${classNameLower}GridPager",{sepclass : 'ui-separator',sepcontent: ''})
    .navButtonAdd('#${classNameLower}GridPager',{
        title:delBtnDesc,
        caption:'',
        buttonicon:"fa-trash",
        onClickButton: function(){
            prepareDelete();
        },
        position:"last"
    }); 
}

function prepareInsert(){
    
    $("#actionType").val("I");
    
    RestfulClient.post(
        contextPath + "${classNameLower}/prepareInsert",
        {
            
        },
        function(data) {
            var ${classNameLower}Dto = data.${classNameLower}Dto;
            for (attr in ${classNameLower}Dto) {
                $("#" + attr).val(${classNameLower}Dto[attr]);
            }
            $("#manageModal").modal("show");
        }
    );
}

function prepareUpdate(){
    
    $("#actionType").val("U");
    
    var selectItems = $("#${classNameLower}Table").jqGrid("getGridParam","selarrrow");
    
    if(selectItems.length <= 0){
        alertMsg(editMsgDesc,"error","");
        return;
    }else if(selectItems.length > 1){
        alertMsg(editMsgDesc,"error","");
        return;
    }else{
        var rowData = $("#${classNameLower}Table").jqGrid("getRowData",selectItems[0]);
        
        RestfulClient.post(
            contextPath + "${classNameLower}/prepareUpdate",
            {
                <#list table.compositeIdColumns as column>
                '${column.columnNameLower}':rowData.${column.columnNameLower}<#if column_has_next>, </#if>
                </#list>
            },
            function(data) {
                var ${classNameLower}Dto = data.${classNameLower}Dto;
                for (attr in ${classNameLower}Dto) {
                    $("#" + attr).val(${classNameLower}Dto[attr]);
                }
                $("#manageModal").modal("show");
            }
        );
    }
}

function deal${className}() {
    
    if(!$("#${classNameLower}Form").valid()){
        return;
    };
    
    var data = $('#${classNameLower}Form').serializeObject();
    var url = null;
    if($("#actionType").val() == "I") {
        url = contextPath + "${classNameLower}/insert";
    }else {
        url = contextPath + "${classNameLower}/update";
    }
    RestfulClient.post(
            url , 
            data ,
            function(data) {
                if(data.flag == "N") {
                    alertMsg(data.msg, "error", "");
                }else {
                    alertMsg(data.msg, "success", "");
                    $("#manageModal").modal("hide");
                    reloadGridTable();
                    validator.resetForm();
                }
            }
    );
}

function prepareDelete(){
        var items=$("#${classNameLower}Table").jqGrid("getGridParam","selarrrow");
        if(items.length <= 0){
            alertMsg(selectMsgDesc,"error","");
        }else{
            alertMsg(deleteRowMsgSureDesc,"confirm","deleteSure()"); //after sure delRowSure()
        }
}

function deleteSure(){
    var list = new Array();
    var selectItems=$("#${classNameLower}Table").jqGrid("getGridParam","selarrrow");
    for(var i = 0;i < selectItems.length;i++){
        var rowData = $("#${classNameLower}Table").jqGrid("getRowData",selectItems[i]);
        list.push( {
            <#list table.compositeIdColumns as column>
            '${column.columnNameLower}':rowData.${column.columnNameLower}<#if column_has_next>, </#if>
            </#list>
        });
    }
    RestfulClient.post(contextPath + "${classNameLower}/delete", {
        "extend" : {
            "${classNameLower}List" : JSON.stringify(list)
        }
    }, function(data) {
        if(data.flag == "N") {
            alertMsg(data.msg, "error", "");
        }else {
            alertMsg(data.msg, "success", "");
            $("#manageModal").modal("hide");
            reloadGridTable();
        }
    });
}

$("#manageModal").draggable({
    handle: ".modal-header",   
    cursor: 'move',   
    refreshPositions: false  
});

function closeModal() {
    validator.resetForm();
}