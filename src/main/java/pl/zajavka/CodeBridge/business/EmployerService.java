package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zajavka.CodeBridge.api.dto.CandidateDTO;
import pl.zajavka.CodeBridge.api.dto.EmployerDTO;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.api.dto.mapper.EmployerMapper;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.business.dao.EmployerDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.Employer;
import pl.zajavka.CodeBridge.domain.JobOffer;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployerService {

    private final EmployerDAO employerDAO;
    private final CandidateDAO candidateDAO;
    private final EmployerMapper employerMapper;
    private final CandidateService candidateService;
    private final CandidateMapper candidateMapper;

    @Autowired
    public EmployerService(EmployerDAO employerDAO, CandidateDAO candidateDAO, EmployerMapper employerMapper, CandidateService candidateService, CandidateMapper candidateMapper) {
        this.employerDAO = employerDAO;
        this.candidateDAO = candidateDAO;
        this.employerMapper = employerMapper;
        this.candidateService = candidateService;
        this.candidateMapper = candidateMapper;
    }

    @Transactional
    public Employer findEmployer(Integer userId) {
        Optional<Employer> employer = employerDAO.findByUserId(userId);
        if (employer.isEmpty()) {
            throw new NotFoundException("Could not find employer by user id: [%s]".formatted(userId));
        }
        return employer.get();
    }

    @Transactional
    public void createJobOffer(JobOffer employerAddJobOffer) {
        employerDAO.createJobOffer(employerAddJobOffer);

    }

    @Transactional
    public Employer findEmployerByEmail(String employerEmail) {
        Optional<Employer> employerByEmail = employerDAO.findEmployerByEmail(employerEmail);
        if (employerByEmail.isEmpty()) {
            throw new NotFoundException("Could not find employer by email: [%s]".formatted(employerByEmail));
        }
        return employerByEmail.get();
    }

    @Transactional
    public List<CandidateDTO> getFilteredCandidates(TechSpecializationsEnum techSpecialization, StatusEnum status) {

        List<Candidate> allCandidates = candidateDAO.findAll();

        return allCandidates.stream()
                .map(candidateMapper::mapToDto)
                .filter(candidate -> techSpecialization == null || techSpecialization.equals(candidate.getTechSpecialization()))
                .filter(candidate -> status == null || status.equals(candidate.getStatus()))
                .sorted(Comparator.comparingInt(CandidateDTO::getCandidateId).reversed())
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CandidateDTO> getAllCandidates() {

        List<Candidate> allCandidates = candidateDAO.findAllCandidates();

        return allCandidates.stream()
                .map(candidateMapper::mapToDto)
                .sorted(Comparator.comparingInt(CandidateDTO::getCandidateId).reversed())
                .collect(Collectors.toList());
    }


    @Transactional(readOnly = true)
    public Employer findLoggedInEmployer() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findEmployerByEmail(email);
    }

    @Transactional(readOnly = true)
    public EmployerDTO getLoggedInEmployerDetails() {
        Employer employer = findLoggedInEmployer();
        return employerMapper.mapToDto(employer);
    }

    @Transactional(readOnly = true)
    public byte[] getCandidateProfilePhoto(String email) {

        Candidate candidateDetails = candidateService.findCandidateByEmail(email);
        CandidateDTO candidateDTO = candidateMapper.mapToDto(candidateDetails);
        return candidateDTO.getProfilePhoto();
    }
}



