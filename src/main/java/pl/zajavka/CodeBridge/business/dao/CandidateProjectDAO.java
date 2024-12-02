package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.CandidateProject;

public interface CandidateProjectDAO {


    CandidateProject createProject(CandidateProject candidateProject);

    void updateCandidateProject(CandidateProject candidateProject);

    void deleteById(Integer candidateProjectId);
}
