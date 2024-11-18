package pl.zajavka.CodeBridge.infrastructure.database.repository.mapper;

import org.hibernate.validator.constraints.LuhnCheck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateEntity;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateExperienceEntity;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CandidateEntityMapper {

    @Mapping(source = "profilePhoto", target = "profilePhoto")
    @Mapping(source = "candidateExperiences", target = "candidateExperiences", qualifiedByName = "mapExperiencesFromEntity")
    Candidate mapFromEntity(CandidateEntity candidateEntity);

    @Named("mapExperiencesFromEntity")
    default List<CandidateExperience> mapExperiencesFromEntity (List<CandidateExperienceEntity> candidateExperiences){
        return candidateExperiences.stream()
                .map(this::mapFromEntity)
                .collect(Collectors.toList());
    }

    @Mapping(target = "candidate.name", ignore = true)
    @Mapping(target = "candidate.surname", ignore = true)
    @Mapping(target = "candidate.email", ignore = true)
    @Mapping(target = "candidate.phone", ignore = true)
    @Mapping(target = "candidate.userId", ignore = true)
    @Mapping(target = "candidate.linkedIn", ignore = true)
    @Mapping(target = "candidate.gitHub", ignore = true)
    @Mapping(target = "candidate.techSpecialization", ignore = true)
    @Mapping(target = "candidate.aboutMe", ignore = true)
    @Mapping(target = "candidate.hobby", ignore = true)
    @Mapping(target = "candidate.candidateSkills", ignore = true)
    @Mapping(target = "candidate.candidateExperiences", ignore = true)
    @Mapping(target = "candidate.profilePhoto", ignore = true)
    CandidateExperience mapFromEntity(CandidateExperienceEntity candidateExperienceEntity);

    @Mapping(source = "profilePhoto", target = "profilePhoto")

    CandidateEntity mapToEntity(Candidate candidate);


}
