package com.hngy.educationaladministration.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.Section;
import com.hngy.educationaladministration.entity.TeacherWithBLOBs;
import com.hngy.educationaladministration.entity.excel.TeacherExcel;
import com.hngy.educationaladministration.mapper.TeacherMapper;
import com.hngy.educationaladministration.service.SectionService;
import com.hngy.educationaladministration.service.TeacherService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class TeacherExcelUtil extends AnalysisEventListener<TeacherExcel> {

    @Getter
    @Setter
    private long countMax = 0;

    @Getter
    @Setter
    private long count = 0;

    @Getter
    @Setter
    private long id_institute = 0;
    //是否发生异常
    @Getter
    @Setter
    private boolean p = false;
    //异常message
    @Getter
    @Setter
    private StringBuilder exceptionMessage = new StringBuilder() ;
    @Getter
    @Setter
    private ArrayList errorData = new ArrayList();


//    以上为自己添加的的参数

    @Autowired
    TeacherService teacherService;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    SectionService sectionService;

    private static final Logger LOGGER = LoggerFactory.getLogger(TeacherExcelUtil.class);
    /**
     * 每隔10条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 100;
    List<TeacherExcel> list = new ArrayList<TeacherExcel>();

    public static long id = 0;

    @Override
    public void invoke(TeacherExcel data, AnalysisContext context) {
        if(data.getUsername()!=null)
        {
//            LOGGER.info("解析到一条数据:{}", JSON.toJSONString(data));
            list.add(data);
            if (list.size() >= BATCH_COUNT) {
                saveData();
                list.clear();
            }
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        saveData();
        LOGGER.info("所有数据解析完成！");

    }

    public void init(){
        errorData = new ArrayList();
        exceptionMessage.setLength(0);
        setId_institute(0);
        setCount(0);
        setCountMax(0);
        setP(false);
        list = new ArrayList<TeacherExcel>();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("读取到{}条数据，开始存储数据库！", list.size());

        Map<String,Long> map = sectionService.getSections(getId_institute()).stream().collect(
                Collectors.toMap(Section::getSectionName,Section::getId)
        );

        Map<String,Integer> sf = new HashMap<>();
        sf.put("教师",0);
        sf.put("审查员",1);

        int count = 0;
        TeacherWithBLOBs teacherWithBLOBs = null;
        TeacherExcel teachere = null;

        for(int i = 0, length = list.size();i < length; i++)
        {
            //遍历出来的 每一项放到TeacherWithBLOBs 中在存入数据库
            teacherWithBLOBs = new TeacherWithBLOBs();
            teachere = list.get(i);

            teacherWithBLOBs.setIdSection(
                    map.get(teachere.getSectionName().trim())
            );
            teacherWithBLOBs.setName(teachere.getName().trim());
            teacherWithBLOBs.setUsername(teachere.getUsername().trim());
            teacherWithBLOBs.setPwd("000000");
            teacherWithBLOBs.setGender(teachere.getGender().trim());
            teacherWithBLOBs.setVerifyFlag(
                    sf.get(teachere.getVerifyFlag().trim())
            );

            try {
                checkValueIsLegal(teacherWithBLOBs);
                count +=teacherService.addTeacher(teacherWithBLOBs,getId_institute());
            } catch (MyException e) {
                teachere.setGender(e.getMessage());
                errorData.add(teachere);
//                exceptionMessage.append(teacherWithBLOBs.getUsername()).append(":").append(e.getMessage()).append("<br>");
//                LOGGER.error(e.getLocalizedMessage());
            }
        }
        setCountMax(list.size());
        setCount(count);
        if(count!=countMax)
            setP(true);
        LOGGER.info("总共{}条数据，存储数据库成功{}条！",list.size(),count);
    }

    //检查值的合法性
    private void checkValueIsLegal(TeacherWithBLOBs teacherWithBLOBs) {
        if(teacherWithBLOBs.getVerifyFlag()==null) {
            throw new MyException("身份填写错误");
        }
        if(teacherWithBLOBs.getIdSection()==null) {
            throw new MyException("没有这个教研室");
        }
        if(teacherWithBLOBs.getUsername().trim().length()<1){
            throw new MyException("账号为空");
        }
        if(teacherWithBLOBs.getName().trim().length()<1){
            throw new MyException("姓名为空");
        }
        if(teacherWithBLOBs.getPwd().trim().length()<1){
            throw new MyException("密码为空");
        }
        if(teacherWithBLOBs.getGender().trim().length()<1){
            throw new MyException("性别为空");
        }
    }

    //用于生成教师excel模板的头部（head）
    public static List<List<String>> head() {
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        List<String> head1 = new ArrayList<String>();
//        List<String> head2 = new ArrayList<String>();
        List<String> head3 = new ArrayList<String>();
        List<String> head4 = new ArrayList<String>();
        List<String> head5 = new ArrayList<String>();
        head0.add("账号");
        head1.add("姓名");
//        head2.add("密码");
        head3.add("性别");
        head4.add("教研室");
        head5.add("身份(教师,审查员)");
        list.add(head0);
        list.add(head1);
//        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        return list;
    }

}
