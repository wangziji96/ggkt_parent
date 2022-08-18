package com.atguigu.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author wzj
 * @Date 2022/8/18 16:26
 */
public class TestWrite {

    public static void main(String[] args) {
        //设置文件名称和路径
        String fileName = "D:\\atguigu.xlsx";
        //调用方法
        EasyExcel.write(fileName,User.class)//相当于创建了一个workbook
                 .sheet("写操作")//建一个sheet,设置sheet的名字
                 .doWrite(data());//往行列单元格写内容，这个方法的参数是list集合

    }

    //循环设置要添加的数据，最终封装到list集合中
    private static List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("张三"+i);
            list.add(data);
        }
        return list;
    }

}
