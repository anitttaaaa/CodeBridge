package pl.zajavka.CodeBridge.infrastructure.database.repository.mapperImpl;

import org.springframework.stereotype.Component;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateProjectEntityMapper;

@Component
public class CandidateProjectEntityMapperImpl implements CandidateProjectEntityMapper {

    @Override
    public CandidateProject mapToDomain(CandidateProjectEntity entity) {
        if (entity == null) {
            return null;
        }

        return new CandidateProject.Builder()
                .candidateProjectId(entity.getCandidateProjectId())
                .projectTitle(entity.getProjectTitle())
                .technologies(entity.getTechnologies())
                .description(entity.getDescription())
                .fromDate(entity.getFromDate())
                .toDate(entity.getToDate())
                .projectLink(entity.getProjectLink())
                .candidateId(entity.getCandidateId())
                .build();
    }

    @Override
    public CandidateProjectEntity mapToEntity(CandidateProject domain) {
        if (domain == null) {
            return null;
        }

        return new CandidateProjectEntity.Builder()
                .candidateProjectId(domain.getCandidateProjectId())
                .projectTitle(domain.getProjectTitle())
                .technologies(domain.getTechnologies())
                .description(domain.getDescription())
                .fromDate(domain.getFromDate())
                .toDate(domain.getToDate())
                .projectLink(domain.getProjectLink())
                .candidateId(domain.getCandidateId())
                .build();
    }
}
