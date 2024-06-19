package pl.zajavka.CodeBridge.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CandidatePortalController {

    private static final String CANDIDATE = "/candidate-portal";

    @GetMapping(value = CANDIDATE)
    public String candidatePortal (){ return "candidate_portal";}

}
