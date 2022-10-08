package com.hngy.educationaladministration.tool;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class mbg {

    public static void main(String[] args) {

        try {
            List<String> warnings = new ArrayList<String>();
            boolean overwrite = true;
            //注意:使用绝对路径 请根据自己本机的具体路径重新配置 否则运行不成功
            File configFile = new File("D:\\但为学习故\\SpringBoot\\bysj\\educationaladministration\\mbg.xml");

            ConfigurationParser cp = new ConfigurationParser(warnings);
            Configuration config = cp.parseConfiguration(configFile);
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            org.mybatis.generator.api.MyBatisGenerator myBatisGenerator = new org.mybatis.generator.api.MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
