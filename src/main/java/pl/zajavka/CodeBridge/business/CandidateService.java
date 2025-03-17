package pl.zajavka.CodeBridge.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import pl.zajavka.CodeBridge.api.dto.*;
import pl.zajavka.CodeBridge.api.dto.mapper.CandidateMapper;
import pl.zajavka.CodeBridge.api.enums.SkillsEnum;
import pl.zajavka.CodeBridge.api.enums.StatusEnum;
import pl.zajavka.CodeBridge.api.enums.TechSpecializationsEnum;
import pl.zajavka.CodeBridge.business.dao.CandidateDAO;
import pl.zajavka.CodeBridge.domain.Candidate;
import pl.zajavka.CodeBridge.domain.CandidateExperience;
import pl.zajavka.CodeBridge.domain.exception.NotFoundException;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CandidateService {

    private final CandidateDAO candidateDAO;
    private final CandidateMapper candidateMapper;

    @Autowired
    public CandidateService(CandidateDAO candidateDAO, CandidateMapper candidateMapper) {
        this.candidateDAO = candidateDAO;
        this.candidateMapper = candidateMapper;

    }

    public CandidateDTO getSortedCandidateDetails(Candidate candidate) {

        CandidateDTO candidateDTO = candidateMapper.mapToDto(candidate);

        List<CandidateExperienceDTO> sortedCandidateExperiences = candidateDTO.getCandidateExperiences()
                .stream()
                .sorted(Comparator.comparing(CandidateExperienceDTO::getFromDate)).collect(Collectors.toList());

        List<CandidateProjectDTO> sortedCandidateProjects = candidateDTO.getCandidateProjects()
                .stream()
                .sorted(Comparator.comparing(CandidateProjectDTO::getFromDate)).collect(Collectors.toList());

        List<CandidateEducationDTO> sortedCandidateEducationStages = candidateDTO.getCandidateEducationStages()
                .stream()
                .sorted(Comparator.comparing(CandidateEducationDTO::getFromDate)).collect(Collectors.toList());

        List<CandidateCourseDTO> sortedCourses = candidateDTO.getCandidateCourses()
                .stream()
                .sorted(Comparator.comparing(CandidateCourseDTO::getFromDate)).collect(Collectors.toList());


        CandidateDTO candidateDetails = CandidateDTO.builder()
                .candidateId(candidate.getCandidateId())
                .name(candidate.getName())
                .surname(candidate.getSurname())
                .email(candidate.getEmail())
                .phone(candidate.getPhone())
                .status(candidate.getStatus())
                .linkedIn(candidate.getLinkedIn())
                .gitHub(candidate.getGitHub())
                .techSpecialization(candidate.getTechSpecialization())
                .aboutMe(candidate.getAboutMe())
                .hobby(candidate.getHobby())
                .profilePhoto(candidate.getProfilePhoto())
                .candidateSkills(candidate.getCandidateSkills())
                .candidateExperiences(sortedCandidateExperiences)
                .candidateProjects(sortedCandidateProjects)
                .candidateEducationStages(sortedCandidateEducationStages)
                .candidateCourses(sortedCourses)
                .build();
        return candidateDetails;
    }

    @Transactional(readOnly = true)
    public Candidate findLoggedInCandidate() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return findCandidateByEmail(email);
    }

    @Transactional
    public Candidate findCandidateByEmail(String email) {
        Optional<Candidate> candidateByEmail = candidateDAO.findCandidateByEmail(email);
        if (candidateByEmail.isEmpty()) {
            throw new NotFoundException("Could not find candidate by email: [%s]".formatted(email));
        }
        return candidateByEmail.get();
    }

    @Transactional
    public CandidateDTO findCandidateByCandidateId(Integer candidateId) {

        Optional<Candidate> candidate = candidateDAO.findById(candidateId);

        return candidate.map(candidateMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Candidate not found for ID: " + candidateId));
    }

    @Transactional
    public void updateCandidateSkills(Authentication authentication, List<SkillsEnum> candidateSkills) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateId)
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(candidateDTO.getStatus())
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateSkills)
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);

    }

    public void updateCandidateTechSpecialization(Authentication authentication, TechSpecializationsEnum techSpecialization) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);


        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateId)
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(candidateDTO.getStatus())
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(techSpecialization)
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);
    }

    public void updateCandidateStatus(Authentication authentication, StatusEnum status) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateId)
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(status)
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);

    }

    public byte[] getProfilePhoto(Authentication authentication) {
        Candidate candidate = findCandidateByEmail(authentication.getName());
        CandidateDTO candidateDTO = candidateMapper.mapToDto(candidate);
        return candidateDTO.getProfilePhoto();
    }

    public void updateCandidateProfilePhoto(Authentication authentication, MultipartFile profilePhoto) throws IOException {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        if (!profilePhoto.isEmpty()) {
            byte[] profilePhotoData = profilePhoto.getBytes();

            CandidateDTO candidateDTO1 = CandidateDTO.builder()
                    .candidateId(candidateDTO.getCandidateId())
                    .name(candidateDTO.getName())
                    .surname(candidateDTO.getSurname())
                    .email(candidateDTO.getEmail())
                    .phone(candidateDTO.getPhone())
                    .status(candidateDTO.getStatus())
                    .linkedIn(candidateDTO.getLinkedIn())
                    .gitHub(candidateDTO.getGitHub())
                    .techSpecialization(candidateDTO.getTechSpecialization())
                    .aboutMe(candidateDTO.getAboutMe())
                    .hobby(candidateDTO.getHobby())
                    .userId(candidateDTO.getUserId())
                    .profilePhoto(profilePhotoData)
                    .candidateSkills(candidateDTO.getCandidateSkills())
                    .candidateExperiences(candidateDTO.getCandidateExperiences())
                    .candidateProjects(candidateDTO.getCandidateProjects())
                    .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                    .candidateCourses(candidateDTO.getCandidateCourses())
                    .build();

            Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
            candidateDAO.updateCandidate(candidateToUpdate);
        }
    }

    public void updateCandidateBasicInfo(Authentication authentication, String name,
                                         String surname, String phone, String linkedIn, String gitHub) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateDTO.getCandidateId())
                .name(name)
                .surname(surname)
                .email(candidateDTO.getEmail())
                .phone(phone)
                .status(candidateDTO.getStatus())
                .linkedIn(linkedIn)
                .gitHub(gitHub)
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);
    }

    public void updateCandidateHobby(Authentication authentication, String hobby) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        if (hobby == null || hobby.trim().isEmpty()) {
            hobby = null;
        }

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateDTO.getCandidateId())
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(candidateDTO.getStatus())
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(candidateDTO.getAboutMe())
                .hobby(hobby)
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);
    }

    public void updateCandidateAboutMe(Authentication authentication, String aboutMe) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        if (aboutMe == null || aboutMe.trim().isEmpty()) {
            aboutMe = null;
        }

        CandidateDTO candidateDTO1 = CandidateDTO.builder()
                .candidateId(candidateDTO.getCandidateId())
                .name(candidateDTO.getName())
                .surname(candidateDTO.getSurname())
                .email(candidateDTO.getEmail())
                .phone(candidateDTO.getPhone())
                .status(candidateDTO.getStatus())
                .linkedIn(candidateDTO.getLinkedIn())
                .gitHub(candidateDTO.getGitHub())
                .techSpecialization(candidateDTO.getTechSpecialization())
                .aboutMe(aboutMe)
                .hobby(candidateDTO.getHobby())
                .userId(candidateDTO.getUserId())
                .profilePhoto(candidateDTO.getProfilePhoto())
                .candidateSkills(candidateDTO.getCandidateSkills())
                .candidateExperiences(candidateDTO.getCandidateExperiences())
                .candidateProjects(candidateDTO.getCandidateProjects())
                .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                .candidateCourses(candidateDTO.getCandidateCourses())
                .build();

        Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
        candidateDAO.updateCandidate(candidateToUpdate);
    }

    public void deleteCandidateProfilePhoto(Authentication authentication) {

        Candidate candidate = findCandidateByEmail(authentication.getName());
        Integer candidateId = candidate.getCandidateId();
        CandidateDTO candidateDTO = findCandidateByCandidateId(candidateId);

        if (!Objects.isNull(candidateDTO.getProfilePhoto())) {
            CandidateDTO candidateDTO1 = CandidateDTO.builder()
                    .candidateId(candidateDTO.getCandidateId())
                    .name(candidateDTO.getName())
                    .surname(candidateDTO.getSurname())
                    .email(candidateDTO.getEmail())
                    .phone(candidateDTO.getPhone())
                    .status(candidateDTO.getStatus())
                    .linkedIn(candidateDTO.getLinkedIn())
                    .gitHub(candidateDTO.getGitHub())
                    .techSpecialization(candidateDTO.getTechSpecialization())
                    .aboutMe(candidateDTO.getAboutMe())
                    .hobby(candidateDTO.getHobby())
                    .userId(candidateDTO.getUserId())
                    .profilePhoto(null)
                    .candidateSkills(candidateDTO.getCandidateSkills())
                    .candidateExperiences(candidateDTO.getCandidateExperiences())
                    .candidateProjects(candidateDTO.getCandidateProjects())
                    .candidateEducationStages(candidateDTO.getCandidateEducationStages())
                    .candidateCourses(candidateDTO.getCandidateCourses())
                    .build();

            Candidate candidateToUpdate = candidateMapper.mapToDomain(candidateDTO1);
            candidateDAO.updateCandidate(candidateToUpdate);
        }
    }

}


