package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateProjectDAO;
import pl.zajavka.CodeBridge.domain.CandidateProject;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateProjectEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateProjectJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateProjectEntityMapper;

@Repository
public class CandidateProjectRepository implements CandidateProjectDAO {

    CandidateProjectEntityMapper candidateProjectEntityMapper;
    CandidateProjectJpaRepository candidateProjectJpaRepository;

    public CandidateProjectRepository(CandidateProjectEntityMapper candidateProjectEntityMapper,
                                      CandidateProjectJpaRepository candidateProjectJpaRepository) {
        this.candidateProjectEntityMapper = candidateProjectEntityMapper;
        this.candidateProjectJpaRepository = candidateProjectJpaRepository;
    }

    @Override
    public CandidateProject createProject(CandidateProject candidateProject) {

        CandidateProjectEntity candidateProjectToSave = candidateProjectEntityMapper.mapToEntity(candidateProject);
        CandidateProjectEntity candidateProjectSaved = candidateProjectJpaRepository.saveAndFlush(candidateProjectToSave);

        return candidateProjectEntityMapper.mapToDomain(candidateProjectSaved);
    }

    @Override
    public void updateCandidateProject(CandidateProject candidateProjectToUpdate) {

        CandidateProjectEntity projectToUpdate = candidateProjectEntityMapper.mapToEntity(candidateProjectToUpdate);
        candidateProjectJpaRepository.saveAndFlush(projectToUpdate);
    }

    @Override
    public void deleteById(Integer candidateProjectId) {

        candidateProjectJpaRepository.deleteById(candidateProjectId);
    }
}
