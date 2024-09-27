package be.pxl.services.domain.dto;

import be.pxl.services.domain.Employee;

import java.util.List;

public record DepartmentRequest(
        Long organizationId,
        String name,
        List<Employee> employees,
        String position
        ) {}
