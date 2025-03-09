package pl.zajavka.CodeBridge.infrastructure.database.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "candidate_course")
public class CandidateCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_course_id")
    private final Integer candidateCourseId;

    @Column(name = "institution")
    private final String institution;

    @Column(name = "course_title")
    private final String courseTitle;

    @Column(name = "description")
    private final String description;

    @Column(name = "technologies")
    private final String technologies;

    @Column(name = "from_date")
    private final LocalDate fromDate;

    @Column(name = "to_date")
    private final LocalDate toDate;

    @Column(name = "candidate_id")
    private final Integer candidateId;

    // Konstruktor prywatny, aby wymusić użycie buildera
    private CandidateCourseEntity(Builder builder) {
        this.candidateCourseId = builder.candidateCourseId;
        this.institution = builder.institution;
        this.courseTitle = builder.courseTitle;
        this.description = builder.description;
        this.technologies = builder.technologies;
        this.fromDate = builder.fromDate;
        this.toDate = builder.toDate;
        this.candidateId = builder.candidateId;
    }

    // Getter
    public Integer getCandidateCourseId() {
        return candidateCourseId;
    }

    public String getInstitution() {
        return institution;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public String getDescription() {
        return description;
    }

    public String getTechnologies() {
        return technologies;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public Integer getCandidateId() {
        return candidateId;
    }

    // Wzorzec buildera
    public static class Builder {

        private Integer candidateCourseId;
        private String institution;
        private String courseTitle;
        private String description;
        private String technologies;
        private LocalDate fromDate;
        private LocalDate toDate;
        private Integer candidateId;

        // Metody setterów zwracające Buildera
        public Builder candidateCourseId(Integer candidateCourseId) {
            this.candidateCourseId = candidateCourseId;
            return this;
        }

        public Builder institution(String institution) {
            this.institution = institution;
            return this;
        }

        public Builder courseTitle(String courseTitle) {
            this.courseTitle = courseTitle;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder technologies(String technologies) {
            this.technologies = technologies;
            return this;
        }

        public Builder fromDate(LocalDate fromDate) {
            this.fromDate = fromDate;
            return this;
        }

        public Builder toDate(LocalDate toDate) {
            this.toDate = toDate;
            return this;
        }

        public Builder candidateId(Integer candidateId) {
            this.candidateId = candidateId;
            return this;
        }

        // Metoda build() do zwrócenia obiektu CandidateCourseEntity
        public CandidateCourseEntity build() {
            return new CandidateCourseEntity(this);
        }
    }

    // Równoważność obiektów na podstawie candidateCourseId
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CandidateCourseEntity that = (CandidateCourseEntity) o;
        return Objects.equals(candidateCourseId, that.candidateCourseId);
    }

    // Haszowanie na podstawie candidateCourseId
    @Override
    public int hashCode() {
        return Objects.hash(candidateCourseId);
    }

    // Reprezentacja tekstowa obiektu
    @Override
    public String toString() {
        return "CandidateCourseEntity{" +
                "candidateCourseId=" + candidateCourseId +
                ", institution='" + institution + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", description='" + description + '\'' +
                ", technologies='" + technologies + '\'' +
                ", fromDate=" + fromDate +
                ", toDate=" + toDate +
                ", candidateId=" + candidateId +
                '}';
    }
}
