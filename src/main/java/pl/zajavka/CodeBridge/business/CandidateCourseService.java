package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import pl.zajavka.CodeBridge.api.dto.CandidateCourseDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateCourseMapper;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateCourse;

import java.nio.file.AccessDeniedException;

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

    public void createCourseData(CandidateCourseDTO candidateCourseFromRequest, Authentication authentication) {


        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();


        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseFromRequest);

        candidateCourseDAO.createCourse(candidateCourse, candidateId);

    }


    public void updateCandidateCourse(CandidateCourseDTO candidateCourseDTO, Authentication authentication) throws AccessDeniedException {

        Candidate candidate = candidateService.findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();

        CandidateCourse candidateCourse = candidateCourseMapper.mapToDomain(candidateCourseDTO);

        candidateCourseDAO.updateCandidateCourse(candidateCourse, candidateId);
    }


    public void deleteCandidateCourseById(Integer candidateCourseId) {
        candidateCourseDAO.deleteById(candidateCourseId);
    }

}
