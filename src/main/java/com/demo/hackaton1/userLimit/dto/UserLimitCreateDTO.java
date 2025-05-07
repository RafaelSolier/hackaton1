package com.demo.hackaton1.userLimit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLimitCreateDTO {
    @NotNull
    private String modelType;
    private Integer maxRequests;
    private Integer maxTokens;
    @NotNull
    private LocalDateTime windowStart;
    @NotNull
    private LocalDateTime windowEnd;
}
