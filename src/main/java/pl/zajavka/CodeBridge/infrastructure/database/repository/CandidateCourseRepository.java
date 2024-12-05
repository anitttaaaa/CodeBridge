package pl.zajavka.CodeBridge.infrastructure.database.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import pl.zajavka.CodeBridge.business.dao.CandidateCourseDAO;
import pl.zajavka.CodeBridge.domain.CandidateCourse;
import pl.zajavka.CodeBridge.infrastructure.database.entity.CandidateCourseEntity;
import pl.zajavka.CodeBridge.infrastructure.database.repository.jpa.CandidateCourseJpaRepository;
import pl.zajavka.CodeBridge.infrastructure.database.repository.mapper.CandidateCourseEntityMapper;

@Repository
@AllArgsConstructor
public class CandidateCourseRepository implements CandidateCourseDAO {

    private final CandidateCourseEntityMapper candidateCourseEntityMapper;
    private final CandidateCourseJpaRepository candidateCourseJpaRepository;

    @Override
    public CandidateCourse createCourse(CandidateCourse candidateCourse) {

        CandidateCourseEntity candidateCourseToSave = candidateCourseEntityMapper.mapToEntity(candidateCourse);
        CandidateCourseEntity candidateCourseSaved = candidateCourseJpaRepository.saveAndFlush(candidateCourseToSave);

        return candidateCourseEntityMapper.mapFromEntity(candidateCourseSaved);
    }

    @Override
    public void updateCandidateCourse(CandidateCourse candidateCourseToUpdate) {
        CandidateCourseEntity educationToSave = candidateCourseEntityMapper.mapToEntity(candidateCourseToUpdate);
        candidateCourseJpaRepository.saveAndFlush(educationToSave);
    }

    @Override
    public void deleteById(Integer candidateCourseId) {
        candidateCourseJpaRepository.deleteById(candidateCourseId);
    }
}
