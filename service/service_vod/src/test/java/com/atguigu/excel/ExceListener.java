package com.atguigu.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

/**
 * @Author wzj
 * @Date 2022/8/18 16:47
 */
public class ExceListener extends AnalysisEventListener<User> {

    //一行一行读取Excel的内容，然后将每行内容封装到user对象
    //从Excel第二行开始读取（第一行是表头）
    @Override
    public void invoke(User user, AnalysisContext analysisContext) {
        System.out.println(user);
    }

    //读取表头内容,这个方法可以到AnalysisEventListener里面复制出来，然后加个@Override注解
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
