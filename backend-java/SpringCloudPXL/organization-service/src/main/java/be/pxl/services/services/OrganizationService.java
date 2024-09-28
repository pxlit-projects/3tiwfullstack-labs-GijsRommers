package be.pxl.services.services;


import be.pxl.services.domain.Organization;
import be.pxl.services.domain.dto.OrganizationResponse;
import be.pxl.services.domain.exceptions.OrganizationNotFoundException;
import be.pxl.services.repository.OrganizationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrganizationService implements IOrganizationService {

    private final OrganizationRepository organizationRepository;

    @Override
    public OrganizationResponse findById(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        return mapToResponse(organization);
    }

    private OrganizationResponse mapToResponse(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .build();
    }

    private OrganizationResponse mapToResponseWithDepartments(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .build();
    }

    private OrganizationResponse mapToResponseWithEmployees(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .employees(organization.getEmployees())
                .build();
    }
    private OrganizationResponse mapToResponseWithEmployeesAndDepartment(Organization organization) {
        return OrganizationResponse.builder()
                .name(organization.getName())
                .address(organization.getAddress())
                .departments(organization.getDepartments())
                .employees(organization.getEmployees())
                .build();
    }
    @Override
    public OrganizationResponse findByIdWithDepartments(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        return mapToResponseWithDepartments(organization);
    }

    @Override
    public OrganizationResponse findByIdWithEmployees(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        return mapToResponseWithEmployees(organization);
    }

    @Override
    public OrganizationResponse findByIdWithDepartmentsAndEmployees(Long id) {
        Organization organization = organizationRepository.findById(id).orElseThrow(() -> new OrganizationNotFoundException("Organization not found"));
        return mapToResponseWithEmployeesAndDepartment(organization);
    }
}
