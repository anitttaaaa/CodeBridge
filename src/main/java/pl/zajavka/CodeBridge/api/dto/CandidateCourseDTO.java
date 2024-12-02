package pl.zajavka.CodeBridge.api.dto;

import lombok.*;

import java.time.LocalDate;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidateCourseDTO {

    private Integer candidateCoursesId;
    private String institution;
    private String courseTitle;
    private String description;
    private String technologies;
    private LocalDate fromDate;
    private LocalDate toDate;
    private Integer candidateId;
}
