package com.hngy.educationaladministration.utils;

import com.hngy.educationaladministration.entity.Subject;
import com.hngy.educationaladministration.entity.SubjectSource;
import com.hngy.educationaladministration.entity.SubjectType;
import com.hngy.educationaladministration.entity.SubjectWithBLOBs;
import com.hngy.educationaladministration.entity.excel.SubjectExcel;
import com.hngy.educationaladministration.mapper.GeneralPurposeMapper;
import com.hngy.educationaladministration.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SubjectExcelUtil {

    @Autowired
    SubjectService subjectService;

    public List<List<String>> head(){
        List<List<String>> list = new ArrayList<List<String>>();
        List<String> head0 = new ArrayList<String>();
        List<String> head1 = new ArrayList<String>();
        List<String> head2 = new ArrayList<String>();
        List<String> head3 = new ArrayList<String>();
        List<String> head4 = new ArrayList<String>();
        List<String> head5 = new ArrayList<String>();
        List<String> head6 = new ArrayList<String>();

        head0.add("毕业设计课题汇总表");head0.add("序号");head0.add("序号");
        head1.add("毕业设计课题汇总表");head1.add("专业");head1.add("专业");
        head2.add("毕业设计课题汇总表");head2.add("指导老师");head2.add("指导老师");
        head3.add("毕业设计课题汇总表");head3.add("课题");head3.add("课题名称");
        head4.add("毕业设计课题汇总表");head4.add("课题");head4.add("课题类型");
        head5.add("毕业设计课题汇总表");head5.add("课题");head5.add("课题来源");
        head6.add("毕业设计课题汇总表");head6.add("选报人数");head6.add("选报人数");

        list.add(head0);
        list.add(head1);
        list.add(head2);
        list.add(head3);
        list.add(head4);
        list.add(head5);
        list.add(head6);
        return list;
    }

    public List<SubjectExcel> getSubjectExcelList(long id_institute){
        List<SubjectWithBLOBs> subjects = subjectService.selectSubjects(0,null,null,id_institute);

        Map<Long,String> types = subjectService.selectSubjectTypes().stream().collect(
                Collectors.toMap(SubjectType::getId, SubjectType::getTypename)
        );

        Map<Long,String> sources = subjectService.selectSubjectSources().stream().collect(
                Collectors.toMap(SubjectSource::getId, SubjectSource::getSourcename)
        );

        List<SubjectExcel> subjectExcels = new ArrayList<>();
        SubjectExcel subjectExcel = new SubjectExcel();

        for(int i = 0; i < subjects.size(); i++){
            Subject subject = subjects.get(i);

            subjectExcel = new SubjectExcel();

            subjectExcel.setId(i+1L);
            subjectExcel.setMarchSpecialty(subject.getMarchspecialty());
            subjectExcel.setTeacherNames(subject.getTeachernames());
            subjectExcel.setProjectName(subject.getProjectname());
            subjectExcel.setProjectType(
                   types.get(subject.getIdProjecttype())
            );
            subjectExcel.setProjectSource(
                   sources.get(subject.getIdProjectsource())
            );
            subjectExcel.setSelectCount(subject.getSelectcount());

//            System.out.println(subjectExcel);
            subjectExcels.add(subjectExcel);
        }

        return subjectExcels;
    }
}
