package com.hngy.educationaladministration.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.hngy.educationaladministration.Exception.MyException;
import com.hngy.educationaladministration.entity.MyClass;
import com.hngy.educationaladministration.entity.StudentWithBLOBs;
import com.hngy.educationaladministration.entity.excel.StudentExcel;
import com.hngy.educationaladministration.mapper.StudentMapper;
import com.hngy.educationaladministration.mapper.TeacherMapper;
import com.hngy.educationaladministration.service.ClassService;
import com.hngy.educationaladministration.service.PublicService;
import com.hngy.educationaladministration.service.StudentService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StudentExcelUtil extends AnalysisEventListener<StudentExcel> {

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
    private StringBuilder exceptionMessage = new StringBuilder();
    //错误数据
    @Getter
    @Setter
    private ArrayList<StudentExcel> errorData = new ArrayList();



    @Autowired
    StudentService studentService;
    @Autowired
    ClassService classService;
    @Autowired
    StudentMapper studentMapper;

    @Autowired
    TeacherMapper teacherMapper;

    @Autowired
    PublicService publicService;

    private static final Logger LOGGER = LoggerFactory.getLogger(StudentExcelUtil.class);
    /**
     * 每隔1000条存储数据库，实际使用中可以3000条，然后清理list ，方便内存回收
     */
    private static final int BATCH_COUNT = 1000;
    List<StudentExcel> list = new ArrayList<StudentExcel>();
    /**
     * 存储用于插入的数据
     */
    List<StudentWithBLOBs> students = new ArrayList<>();

    public static long id = 0;

    @Override
    public void invoke(StudentExcel data, AnalysisContext context) {
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
        errorData = new ArrayList<StudentExcel>();
        exceptionMessage.setLength(0);
        setId_institute(0);
        setCount(0);
        setCountMax(0);
        setP(false);
        list = new ArrayList<StudentExcel>();
        students = new ArrayList<StudentWithBLOBs>();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        LOGGER.info("读取到{}条数据，开始存储数据库！", list.size());

        Map<String,Long> map = classService.getClasses(-1,null,null,getId_institute()).stream().collect(
                Collectors.toMap(MyClass::getClassName,MyClass::getId)
        );

        int count = 0;
        StudentWithBLOBs student = null;
        StudentExcel studentExcel = null;

        for(int i = 0 , length = list.size() ;i < length; i++ )
        {
            //遍历出来的 每一项放到TeacherWithBLOBs 中在存入数据库
            student = new StudentWithBLOBs();
            studentExcel = list.get(i);
            student.setIdClass(
                    map.get(studentExcel.getClassName().trim())
            );
            if(studentExcel.getName()!=null){
                student.setName(studentExcel.getName().trim());
            }else {
                //姓名，为空则表示该学生 退学
                //暂时用性别列显示错误
                studentExcel.setGender("学生姓名为空");
                errorData.add(studentExcel);
                continue;
            }
            student.setUsername(studentExcel.getUsername().trim());
            student.setPwd("000000");
            student.setGender(studentExcel.getGender().trim());
            student.setStunum(studentExcel.getStunum().trim());

            try {
                //值判断
                checkValueIsLegal(student);
                //添加到待插入的Arraylist
                students.add(student);
                //导入成功加1
                count++;
            } catch (MyException e) {
                //暂时用性别列显示错误
                studentExcel.setGender(e.getMessage());
                errorData.add(studentExcel);
//                LOGGER.error(student.getUsername()+":"+e.getMessage());
            }
        }

        setCountMax(list.size());
        setCount(count);
        if(count!=countMax)
            setP(true);

        LOGGER.info("总共{}条数据，存储数据库成功{}条！",list.size(),count);

        // 如果数据为空则不导入
        if(students.size()==0){
            return ;
        }
        //批量插入
        studentMapper.batchInsert(students);
    }

    //检查值的合法性
    private void checkValueIsLegal(StudentWithBLOBs student) throws MyException {

        if(student.getIdClass()==null){
            throw new MyException("没有这个班级");
        }

        if(student.getName().trim().length()<1){
            throw new MyException("名字为空");
        }

        if(student.getStunum().trim().length()<1){
            throw new MyException("学号为空");
        }

        if(student.getGender().trim().length()<1){
            throw new MyException("性别为空");
        }

        if(student.getPwd().trim().length()<1){
            throw new MyException("密码为空");
        }

        if(student.getUsername().trim().length()<1){
            throw new MyException("账号为空");
        }

        publicService.CheckIfTheUsernameIsDuplicated(student.getUsername());


    }

}
