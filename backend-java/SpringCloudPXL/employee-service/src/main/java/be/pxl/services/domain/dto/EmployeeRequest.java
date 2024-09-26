package be.pxl.services.domain.dto;


public record EmployeeRequest (
        Long organizationId,
        Long departmentId,
        String name,
        int age,
        String position
        ) {}
