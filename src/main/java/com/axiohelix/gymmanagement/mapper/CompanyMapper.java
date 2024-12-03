package com.axiohelix.gymmanagement.mapper;


import com.axiohelix.gymmanagement.entity.Company;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CompanyMapper {

    void insert(Company company);
    List<Company> selectAll();
    List<Company>selectById(String id);
    void update(Company company);
    void delete(String id);
}
