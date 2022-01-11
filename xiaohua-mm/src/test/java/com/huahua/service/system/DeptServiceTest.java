package com.huahua.service.system;


import com.huahua.config.SpringConfig;
import com.huahua.domain.system.Dept;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class DeptServiceTest {
    @Autowired
    private DeptService deptService;

    @Test
    public void findByIdTest(){
        Dept dept = deptService.findById("100");

        System.out.println(deptService.findById("100"));
        System.out.println(dept.getDeptName());
    }

}
