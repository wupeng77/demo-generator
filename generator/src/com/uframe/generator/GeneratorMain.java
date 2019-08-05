package com.uframe.generator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lujicong
 */
public class GeneratorMain {

    /**
     * #############需要生成的表加如list中,可以批量生成，也可以单表生成###########
     */
    public static List<String> initList() {
        List<String> list = new ArrayList<String>();
//        list.add("p_product_class");
//        list.add("p_product");
//        list.add("p_product_info");
//        list.add("u_user");
//        list.add("u_user_address");
        list.add("t_order_detial");
        list.add("t_order");
        return list;
    }

    /**
     * 请直接修改以下代码调用不同的方法以执行相关生成任务.
     */
    public static void main(String[] args) throws Exception {
        /**
         * 有三点需要引起特别的注意 
         * (1)数据库连接等一些配置在generator.xml中进行配置
         * (2)表无主键，无法生成 
         * (3)外键关联的表无读权限，无法生成
         */
        GeneratorFacade g = new GeneratorFacade();
        generateByTable(g, "uframe_20160104");// 新框架下的模板
        Runtime.getRuntime().exec("cmd.exe /c start " + GeneratorProperties.getRequiredProperty("outRoot"));
    }

    /**
     * 通过数据库表生成文件
     */
    public static void generateByTable(GeneratorFacade g, String template) throws Exception {
        for (String string : initList()) {
            g.generateByTable(string, template); // 通过数据库表生成文件,template为模板的根目录

        }
    }

    public static void generateByProcedure(GeneratorFacade g, String procedureName, String template) throws Exception {
        g.generateByProcedure(procedureName, template);// 通过存储过程生成文件
    }
}
