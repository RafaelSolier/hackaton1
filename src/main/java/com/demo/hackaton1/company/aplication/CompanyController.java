package com.demo.hackaton1.company.aplication;

import com.demo.hackaton1.company.domain.Company;
import com.demo.hackaton1.company.domain.CompanyService;
import com.demo.hackaton1.company.dto.CompanyCreateDTO;
import com.demo.hackaton1.company.dto.CompanyDTO;
import com.demo.hackaton1.company.dto.CompanyUpdateDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;
    private final ModelMapper mapper;
    @GetMapping("/hello")
    public String hello() {
        return "App funcionando correctamente.";
    }

    @PostMapping
    public ResponseEntity<CompanyDTO> create(@Valid @RequestBody CompanyCreateDTO dto) {
        Company entity = mapper.map(dto, Company.class);
        Company saved = companyService.createCompany(entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.map(saved, CompanyDTO.class));
    }

    @GetMapping
    public List<CompanyDTO> list() {
        return companyService.getAllCompanies()
                .stream()
                .map(c -> mapper.map(c, CompanyDTO.class))
                .toList();
    }

    @GetMapping("/{id}")
    public CompanyDTO getOne(@PathVariable Long id) {
        return mapper.map(
                companyService.getCompanyById(id),
                CompanyDTO.class
        );
    }

    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable Long id,
                             @Valid @RequestBody CompanyUpdateDTO dto) {
        Company entity = mapper.map(dto, Company.class);
        return mapper.map(
                companyService.updateCompany(id, entity),
                CompanyDTO.class
        );
    }

    @PatchMapping("/{id}/status")
    public CompanyDTO toggleStatus(@PathVariable Long id,
                                   @RequestParam boolean active) {
        return mapper.map(
                companyService.toggleCompanyStatus(id, active),
                CompanyDTO.class
        );
    }

    @GetMapping("/{id}/consumption")
    public ResponseEntity<Map<String, Object>> consumption(@PathVariable Long id) {
        return ResponseEntity.ok(companyService.getCompanyConsumption(id));
    }
}