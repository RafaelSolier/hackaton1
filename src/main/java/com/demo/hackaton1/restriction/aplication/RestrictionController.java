package com.demo.hackaton1.restriction.aplication;

import com.demo.hackaton1.restriction.domain.Restriction;
import com.demo.hackaton1.restriction.domain.RestrictionService;
import com.demo.hackaton1.restriction.dto.RestrictionCreateDTO;
import com.demo.hackaton1.restriction.dto.RestrictionDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/company/restrictions")
@RequiredArgsConstructor
public class RestrictionController {

    private final RestrictionService restrictionService;
    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<RestrictionDTO> create(
            @RequestParam Long companyId,
            @Valid @RequestBody RestrictionCreateDTO dto) {
        Restriction entity = mapper.map(dto, Restriction.class);
        Restriction saved = restrictionService.createRestriction(companyId, entity);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapper.map(saved, RestrictionDTO.class));
    }

    @GetMapping
    public List<RestrictionDTO> list(@RequestParam Long companyId) {
        return restrictionService.getRestrictionsByCompany(companyId)
                .stream()
                .map(r -> mapper.map(r, RestrictionDTO.class))
                .toList();
    }

    @PutMapping("/{id}")
    public RestrictionDTO update(@PathVariable Long id,
                                 @Valid @RequestBody RestrictionCreateDTO dto) {
        Restriction entity = mapper.map(dto, Restriction.class);
        return mapper.map(
                restrictionService.updateRestriction(id, entity),
                RestrictionDTO.class
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        restrictionService.deleteRestriction(id);
    }
}