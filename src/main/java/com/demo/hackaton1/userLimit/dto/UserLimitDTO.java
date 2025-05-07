package com.demo.hackaton1.userLimit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLimitDTO {
    private Long id;
    private String modelType;
    private Integer maxRequests;
    private Integer maxTokens;
    private LocalDateTime windowStart;
    private LocalDateTime windowEnd;
}
