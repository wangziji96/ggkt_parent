package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

/**
 * @Author wzj
 * @Date 2022/8/18 16:54
 */
public class TestRead {

    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\atguigu.xlsx";
        //调用方法进行读操作
        EasyExcel.read(fileName,User.class,new ExceListener()).sheet("sheet名").doRead();
    }

}
