package pl.zajavka.CodeBridge.api.dto.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.domain.Employer;

@Component
public class EmployerMapperImpl implements EmployerMapper {

    @Override
    public EmployerDTO mapToDto(Employer employer) {
        return new EmployerDTO.Builder()
                .employerId(employer.getEmployerId())
                .companyName(employer.getCompanyName())
                .email(employer.getEmail())
                .nip(employer.getNip())
                .build();
    }

    @Override
    public Employer mapToDomain(EmployerDTO employerDTO) {
        return new Employer.EmployerBuilder()
                .employerId(employerDTO.getEmployerId())
                .companyName(employerDTO.getCompanyName())
                .email(employerDTO.getEmail())
                .nip(employerDTO.getNip())
                .build();
    }


}
