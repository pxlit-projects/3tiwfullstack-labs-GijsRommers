package be.pxl.services.services;

import be.pxl.services.domain.Department;
import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.DepartmentRequest;
import be.pxl.services.domain.dto.DepartmentResponse;
import be.pxl.services.domain.exceptions.DepartmentNotFoundException;
import be.pxl.services.repository.DepartmentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService implements IDepartmentService {

    private final DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentResponse> getAllDepartments() {
        List<Department> departments =  departmentRepository.findAll();
        return departments.stream().map(this::mapToDepartmentResponse).toList();
    }

    private DepartmentResponse mapToDepartmentResponse(Department department) {
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .position(department.getPosition())
                .build();
    }

    private DepartmentResponse mapToDepartmentResponseWithEmployees(Department department) {
        return DepartmentResponse.builder()
                .organizationId(department.getOrganizationId())
                .name(department.getName())
                .employees(department.getEmployees())
                .position(department.getPosition())
                .build();
    }

    @Override
    @Transactional
    public void addDepartment(DepartmentRequest departmentRequest) {
        Department department = Department.builder()
                .organizationId(departmentRequest.organizationId())
                .name(departmentRequest.name())
                .position(departmentRequest.position())
                .build();
        departmentRepository.save(department);
    }

    @Override
    public DepartmentResponse findById(Long id) {
        return departmentRepository.findById(id).map(this::mapToDepartmentResponse).orElseThrow( () -> new DepartmentNotFoundException("Department with ID: " + id + " not found"));
    }

    @Override
    public List<DepartmentResponse> findByOrganization(Long organizationId) {
        return departmentRepository.findDepartmentsByOrganizationId(organizationId).stream().map(this::mapToDepartmentResponse).toList();
    }

    @Override
    public List<DepartmentResponse> findByOrganizationWithEmployees(Long organizationId) {
        return departmentRepository.findDepartmentsByOrganizationId(organizationId).stream().map(this::mapToDepartmentResponseWithEmployees).toList();
    }
}
