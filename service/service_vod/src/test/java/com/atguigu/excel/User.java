package com.atguigu.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author wzj
 * @Date 2022/8/18 16:23
 */
@Data
public class User {

    @ExcelProperty(value = "用户编号",index = 0)//设置表头信息
    private int id;

    @ExcelProperty(value = "用户名称",index = 1)
    private String name;

}
