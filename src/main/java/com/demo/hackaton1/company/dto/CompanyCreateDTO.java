package com.demo.hackaton1.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyCreateDTO {
    @NotNull
    private String name;
    @NotNull
    private String ruc;
    @NotNull
    private LocalDate affiliationDate;
}