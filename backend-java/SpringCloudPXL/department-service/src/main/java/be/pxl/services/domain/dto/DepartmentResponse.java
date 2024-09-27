package be.pxl.services.domain.dto;

import be.pxl.services.domain.Employee;
import lombok.Builder;

import java.util.List;

@Builder
public record DepartmentResponse(
        Long organizationId,
        String name,
        List<Employee> employees,
        String position
        ) {}
