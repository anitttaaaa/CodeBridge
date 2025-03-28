package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCourseMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

@Service
public class CandidateCourseService {

    private final CandidateService candidateService;
    private final CandidateCourseDAO candidateCourseDAO;
    private final CandidateCourseMapper candidateCourseMapper;

    @Autowired
    public CandidateCourseService(CandidateService candidateService,
                                  CandidateCourseDAO candidateCourseDAO,
                                  CandidateCourseMapper candidateCourseMapper) {
        this.candidateService = candidateService;
        this.candidateCourseDAO = candidateCourseDAO;
        this.candidateCourseMapper = candidateCourseMapper;
    }

    public void createCourseData(CandidateCourseDTO candidateCourseDTO, Authentication authentication) {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseDTO);

        if (candidateCourse == null) {
            throw new NullPointerException("Mapping CandidateCourseDTO to CandidateCourse failed");
        }

        candidateCourseDAO.createCourse(candidateCourse, candidateId);
    }


    public void updateCandidateCourse(CandidateCourseDTO candidateCourseDTO, Authentication authentication) {

        if (candidateCourseDTO == null) {
            throw new NullPointerException("CandidateCourseDTO cannot be null");
        }

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseDTO);

        if (candidateCourse == null) {
            throw new NullPointerException("Mapping to CandidateCourse failed");
        }

        candidateCourseDAO.updateCandidateCourse(candidateCourse, candidateId);
    }


    public void deleteCandidateCourseById(Integer candidateCourseId) {
        candidateCourseDAO.deleteById(candidateCourseId);
    }

}
