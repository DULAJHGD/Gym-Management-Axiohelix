package com.axiohelix.gymmanagement.service;

import com.axiohelix.gymmanagement.entity.Company;
import com.axiohelix.gymmanagement.entity.MemberData;
import com.axiohelix.gymmanagement.mapper.CompanyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyMapper companyMapper;

    public void insertCompany(Company company) {
        companyMapper.insert(company);
    }

    public List<Company> getCompanyById(String memberId) {
        return companyMapper.selectById(memberId);
    }

    public List<Company> getAllCompanies() {
        return companyMapper.selectAll();
    }

    public void updateCompany(Company company) {
        companyMapper.update(company);
    }

    public void deleteCompany(String Id) {
        companyMapper.delete(Id);
    }

}
