package pl.zajavka.CodeBridge.business.dao;

import pl.zajavka.CodeBridge.domain.JobApplication;

import java.util.List;
import java.util.Optional;

public interface JobApplicationDAO {

    void createJobApplication(JobApplication jobApplication);

    List<JobApplication> findApplicationsByCandidateId(Integer candidateId);

    List<JobApplication> findEmployerJobApplicationsByEmployerId(Integer employerId);
}
