package be.pxl.services.services;

import be.pxl.services.domain.Employee;
import be.pxl.services.domain.dto.EmployeeRequest;
import be.pxl.services.domain.dto.EmployeeResponse;
import be.pxl.services.domain.exceptions.EmployeeNotFoundException;
import be.pxl.services.repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService implements IEmployeeService{

    private final EmployeeRepository employeeRepository;

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employees =  employeeRepository.findAll();
        return employees.stream().map(this::mapToEmployeeResponse).toList();
    }

    private EmployeeResponse mapToEmployeeResponse(Employee employee) {
        return EmployeeResponse.builder()
                .age(employee.getAge())
                .name(employee.getName())
                .position(employee.getPosition())
                .departmentId(employee.getDepartmentId())
                .organizationId(employee.getOrganizationId())
                .build();

    }

    @Override
    @Transactional
    public void addEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Employee.builder()
                .age(employeeRequest.age())
                .name(employeeRequest.name())
                .position(employeeRequest.position())
                .departmentId(employeeRequest.departmentId())
                .organizationId(employeeRequest.organizationId())
                .build();
        employeeRepository.save(employee);
    }

    @Override
    public EmployeeResponse findById(Long id) {
        return employeeRepository.findById(id).map(this::mapToEmployeeResponse).orElseThrow( () -> new EmployeeNotFoundException("Employee with ID: " + id + " not found"));
    }

    @Override
    public List<EmployeeResponse> findByDepartment(Long departmentId) {
        return employeeRepository.findEmployeesByDepartmentId(departmentId).stream().map(this::mapToEmployeeResponse).toList();
    }

    @Override
    public List<EmployeeResponse> findByOrganization(Long organizationId) {
        return employeeRepository.findEmployeesByOrganizationId(organizationId).stream().map(this::mapToEmployeeResponse).toList();
    }
}
