package be.pxl.services.domain.dto;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import lombok.Builder;

import java.util.List;

@Builder
public record OrganizationResponse(
        String name,
        String address,
        List<Employee> employees,
        List<Department> departments
) {
}
