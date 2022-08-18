package com.atguigu.ggkt.vod.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.ggkt.exception.GgktException;
import com.atguigu.ggkt.model.vod.Subject;
import com.atguigu.ggkt.vo.vod.SubjectEeVo;
import com.atguigu.ggkt.vod.mapper.SubjectMapper;
import com.atguigu.ggkt.vod.service.SubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2022-08-18
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    //课程分类列表
    //懒加载，每次查询一层数据
    @Override
    public List<Subject> selectSubjectList(Long id) {
        //SELECT * FROM SUBJECT WHERE parent_id=0
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",id);
        List<Subject> subjectList = baseMapper.selectList(wrapper);
        //subjectList遍历，得到每个subject对象，判断是否有下一层数据，有hasChildren=true
        for (Subject subject:subjectList){
            //获取sublectid
            Long subjectId = subject.getId();
            //查询
            boolean isChild = this.isChildren(subjectId);
            //封装到对象里面
            subject.setHasChildren(isChild);
        }
        return subjectList;
    }

    //课程分类导出
    @Override
    public void exportData(HttpServletResponse response) {
        try {
            //设置下载信息，设置数据格式
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");//你会发现你下载的文件叫课程分类
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");

            //查询课程分类表所有数据
            List<Subject> subjectList = baseMapper.selectList(null);

            //将List<Subject> 转换成 List<SubjectEeVo>,因为实体类Subject和SubjectEeVo属性不同，SubjectEeVo才是我们要写进Excel
            List<SubjectEeVo> subjectEeVoList = new ArrayList<>();
            for (Subject subject: subjectList) {
                SubjectEeVo subjectEeVo = new SubjectEeVo();
                subjectEeVo.setId(subject.getId());
                subjectEeVo.setParentId(subject.getParentId());

                //可以将数据进行复制。找到属性一致的，进行复制。这是spring框架提供的，如果不喜欢，可以用上面传统的方式
//                BeanUtils.copyProperties(subject,subjectEeVo);

                subjectEeVoList.add(subjectEeVo);
            }

            //EasyExcel写操作
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class)
                    .sheet("课程分类")
                    .doWrite(subjectEeVoList);
        }catch (Exception e){
            throw new GgktException(20001,"导出异常");
        }

    }

    //判断是否有下一层数据
    private boolean isChildren(Long subjectId) {
        QueryWrapper<Subject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id",subjectId);
        Integer count = baseMapper.selectCount(wrapper);
        //1>0 true 0 > 0false
        return count > 0;
    }
}
