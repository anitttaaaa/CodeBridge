package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateProject;

public interface CandidateProjectDAO {


    CandidateProject createProject(CandidateProject candidateProject, Integer candidateId);

    void updateCandidateProject(CandidateProject candidateProject, Integer candidateId);

    void deleteById(Integer candidateProjectId);
}
