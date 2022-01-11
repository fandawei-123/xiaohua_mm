package com.huahua.service.store;

import com.github.pagehelper.PageInfo;
import com.huahua.config.SpringConfig;
import com.huahua.domain.store.Company;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class CompanyServiceTest {
    @Autowired
    private CompanyService companyService;


    @Test
    public void testSave() {
        Company company = new Company();
        company.setName("测试数据2");
        companyService.save(company);
    }

    @Test
    public void testFindById(){
        Company byId = companyService.findById("1");
        System.out.println(byId.getName());
    }

    @Test
    public void testFindAll() {
        PageInfo all = companyService.findAll(1, 100);
        System.out.println(all);
    }

}
