package com.sxgy.sp52.core.util.mybatis;

import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.Method;
import org.mybatis.generator.api.dom.java.TopLevelClass;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author SXGY_09
 * @description 描述
 * @date 2019-11-23 13:17
 */
public class LombokPlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean modelBaseRecordClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addImportedType("lombok.Data");
        topLevelClass.addImportedType("lombok.NoArgsConstructor");
        topLevelClass.addImportedType("lombok.AllArgsConstructor");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModel");
        topLevelClass.addImportedType("io.swagger.annotations.ApiModelProperty");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.IdType");
        topLevelClass.addImportedType("com.baomidou.mybatisplus.annotation.TableId");
        topLevelClass.addAnnotation("@Data");
        topLevelClass.addAnnotation("@NoArgsConstructor");
        topLevelClass.addAnnotation("@AllArgsConstructor");
        topLevelClass.addAnnotation("@ApiModel(value = "  +"\""+introspectedTable.getFullyQualifiedTableNameAtRuntime() +"\""+ ")");
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * @description "+introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" * @author "+properties.getProperty("author.name"));
        topLevelClass.addJavaDocLine(" * @date "+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        topLevelClass.addJavaDocLine(" */");
        return true;
    }

    @Override
    public boolean modelSetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }

    @Override
    public boolean modelGetterMethodGenerated(Method method, TopLevelClass topLevelClass, IntrospectedColumn introspectedColumn, IntrospectedTable introspectedTable, ModelClassType modelClassType) {
        return false;
    }
}
