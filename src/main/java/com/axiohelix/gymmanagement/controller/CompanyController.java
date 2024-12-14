package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.dto.CompanyDto;
import com.axiohelix.gymmanagement.entity.Company;
import com.axiohelix.gymmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public void addCompany(@RequestBody CompanyDto companyDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setAddress(companyDto.getAddress());
        company.setCompanyRegisterNo(companyDto.getCompanyRegisterNo());
        company.setPhoneNo(companyDto.getPhoneNo());
        company.setCreatedBy(loggedInUserEmail);

        companyService.insertCompany(company);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getAllCompany() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Company> getCompanyById(@RequestParam String id ,@RequestBody Company company) {
        return companyService.getCompanyById(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateCompany(@RequestBody CompanyDto companyDto) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUserEmail = authentication.getName(); // Assuming `getName()` returns the user ID.

        Company company = new Company();
        company.setName(companyDto.getName());
        company.setEmail(companyDto.getEmail());
        company.setAddress(companyDto.getAddress());
        company.setCompanyRegisterNo(companyDto.getCompanyRegisterNo());
        company.setPhoneNo(companyDto.getPhoneNo());
        company.setLastModifiedBy(loggedInUserEmail);
        company.setLastModifiedOn(String.valueOf(LocalDateTime.now()));

        companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteCompany(@RequestParam String id, @RequestBody Company company) {
        companyService.deleteCompany(id);
    }
}
