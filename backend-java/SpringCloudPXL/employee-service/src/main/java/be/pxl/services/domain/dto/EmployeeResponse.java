package be.pxl.services.domain.dto;

import lombok.Builder;

@Builder
public record EmployeeResponse(
        Long organizationId,
        Long departmentId,
        String name,
        int age,
        String position
        ) {}