package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;

import java.util.List;

public interface IDepartmentService {
    List<DepartmentResponse> getAllDepartments();
    void addDepartment(DepartmentRequest departmentRequest);
    DepartmentResponse findById(Long id);
    List<DepartmentResponse> findByOrganization(Long organizationId);
    List<DepartmentResponse> findByOrganizationWithEmployees(Long organizationId);

}
