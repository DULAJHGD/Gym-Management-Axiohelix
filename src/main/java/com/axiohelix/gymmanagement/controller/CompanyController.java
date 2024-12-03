package com.axiohelix.gymmanagement.controller;

import com.axiohelix.gymmanagement.entity.Company;
import com.axiohelix.gymmanagement.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping
    public void addCompany(@RequestBody Company company) {
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
    public void updateCompany(@RequestBody Company company) {
        companyService.updateCompany(company);
    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@RequestParam String id, @RequestBody Company company) {
        companyService.deleteCompany(id);
    }
}
