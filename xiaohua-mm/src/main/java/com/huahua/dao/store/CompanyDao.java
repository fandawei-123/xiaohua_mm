package com.huahua.dao.store;

import com.huahua.domain.store.Company;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Huahua
 */
@Repository
public interface CompanyDao {


    @Insert("insert into st_company (id, name, expiration_date,\n" +
            "        address, license_id, representative,\n" +
            "        phone, company_size, industry,\n" +
            "        remarks, state,\n" +
            "        city)\n" +
            "        values (#{id}, #{name}, #{expirationDate},\n" +
            "        #{address}, #{licenseId}, #{representative},\n" +
            "        #{phone}, #{companySize}, #{industry},\n" +
            "        #{remarks}, #{state},\n" +
            "        #{city})")
    void save(Company company);

    @Delete("delete from st_company where id = #{id}")
    void delete(Company company);

    @Update("update st_company\n" +
            "        set name = #{name},\n" +
            "        expiration_date = #{expirationDate},\n" +
            "        address = #{address},\n" +
            "        license_id = #{licenseId},\n" +
            "        representative = #{representative},\n" +
            "        phone = #{phone},\n" +
            "        company_size = #{companySize},\n" +
            "        industry = #{industry},\n" +
            "        remarks = #{remarks},\n" +
            "        state = #{state},\n" +
            "        city = #{city}\n" +
            "        where id = #{id}")
    void update(Company company);

    @Select("select\n" +
            "id, name, expiration_date, address, license_id, representative, phone, company_size,industry, remarks, state, city\n" +
            "from st_company\n" +
            "where id = #{id}")
    Company findById(String id);


    @Select("select\n" +
            "id, name, expiration_date, address, license_id, representative, phone, company_size,industry, remarks, state, city\n" +
            "from st_company")
    List<Company> findAll();
}
