package pl.zajavka.CodeBridge.api.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@With
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CandidatePortalDTO {


    private Integer candidateId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String gitHub;
    private String linkedIn;
    private Integer userId;

    private byte[] profilePhoto;

}
