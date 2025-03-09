package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.CandidateEducation;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEducationEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateEducationEntityMapper;

@Component
public class CandidateEducationEntityMapperImpl implements CandidateEducationEntityMapper {

    @Override
    public CandidateEducation mapFromEntity(CandidateEducationEntity candidateEducationEntity) {


        return new CandidateEducation.Builder()
                .candidateEducationId(candidateEducationEntity.getCandidateEducationId())
                .institution(candidateEducationEntity.getInstitution())
                .degree(candidateEducationEntity.getDegree())
                .fieldOfStudy(candidateEducationEntity.getFieldOfStudy())
                .fromDate(candidateEducationEntity.getFromDate())
                .toDate(candidateEducationEntity.getToDate())
                .candidateId(candidateEducationEntity.getCandidateId())
                .build();
    }
    @Override
    public CandidateEducationEntity mapToEntity(CandidateEducation candidateEducation) {


        return new CandidateEducationEntity.Builder()
                .candidateEducationId(candidateEducation.getCandidateEducationId())
                .institution(candidateEducation.getInstitution())
                .degree(candidateEducation.getDegree())
                .fieldOfStudy(candidateEducation.getFieldOfStudy())
                .fromDate(candidateEducation.getFromDate())
                .toDate(candidateEducation.getToDate())
                .candidateId(candidateEducation.getCandidateId())
                .build();
    }
}
