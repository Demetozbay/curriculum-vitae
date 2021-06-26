package com.demet.curriculumvitae.controller;

import com.demet.curriculumvitae.exception.UnableToProcess;
import com.demet.curriculumvitae.exception.Unauthorised;
import com.demet.curriculumvitae.helper.IsAdmin;
import com.demet.curriculumvitae.helper.IsAuthenticated;
import com.demet.curriculumvitae.helper.IsOwned;
import com.demet.curriculumvitae.model.cv.CurriculumVitae;
import com.demet.curriculumvitae.model.cv.Section;
import com.demet.curriculumvitae.model.cv.section.skills.Skill;
import com.demet.curriculumvitae.model.cv.section.skills.Skills;
import com.demet.curriculumvitae.payload.section.*;
import com.demet.curriculumvitae.service.CurriculumVitaeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cv")
public class CurriculumVitaeController {
    private final CurriculumVitaeService service;
    private final ObjectMapper objectMapper;

    public CurriculumVitaeController(CurriculumVitaeService service,
                                     ObjectMapper objectMapper) {
        this.service = service;
        this.objectMapper = objectMapper;
    }

    @GetMapping
    @IsAdmin
    public Page<CurriculumVitae> get(@RequestParam Map<String, Object> queryParams) {
        return service.getByPage(queryParams);
    }

    @GetMapping("/{username}")
    @IsAuthenticated
    @IsOwned
    public Page<CurriculumVitae> get(@PathVariable String username,
                                     @RequestParam Map<String, Object> queryParams) {
        return service.getByPage(username, queryParams);
    }

    @GetMapping("/{username}/{id}")
    @IsAuthenticated
    @IsOwned
    public CurriculumVitae get(@PathVariable String username,
                               @PathVariable String id) throws Unauthorised {
        CurriculumVitae vitae = service.getById(id);
        if (vitae.getUsername()
                 .equals(username))
            return vitae;
        throw new Unauthorised("You don't have enough permissions to see this cv");
    }

    @PostMapping("/{username}")
    @IsAuthenticated
    @IsOwned
    public CurriculumVitae create(@PathVariable String username) {
        return service.create(username);
    }

    @DeleteMapping("/{username}/{id}")
    @IsAuthenticated
    @IsOwned
    public ResponseEntity<?> delete(@PathVariable String username,
                                    @PathVariable String id) throws Unauthorised {
        CurriculumVitae vitae = service.getById(id);
        if (!vitae.getUsername()
                  .equals(username))
            throw new Unauthorised("You don't have enough permissions to see this cv");
        service.deleteById(id);
        return ResponseEntity.ok()
                             .body(Map.of("status", "ok"));
    }

    // ================================================================================================================

    @PutMapping("/{username}/{id}")
    @IsAuthenticated
    @IsOwned
    public CurriculumVitae addSection(@PathVariable String username,
                                      @PathVariable String id,
                                      @Valid @RequestBody Map<String, Object> request)
    throws UnableToProcess, Unauthorised {
        if (!request.containsKey("type"))
            throw new UnableToProcess("Section type not found in request body");

        CurriculumVitae vitae = service.getById(id);
        if (!vitae.getUsername()
                  .equals(username))
            throw new Unauthorised("You don't have enough permissions to see this cv");

        if (request.get("type")
                   .equals("skill")) {
            Supplier<Skill> skillSupplier = objectMapper
                    .convertValue(request, SkillRequest.class);
            Skills skills = (Skills) vitae.getSectionList()
                                          .stream()
                                          .filter(section -> section instanceof Skills)
                                          .findFirst()
                                          .orElse(new Skills());
            if (skills.getSkillList() == null)
                skills.setSkillList(new ArrayList<>());
            skills.getSkillList()
                  .add(skillSupplier.get());

            vitae.setSectionList(
                    vitae.getSectionList()
                         .stream()
                         .filter(section -> !(section instanceof Skills))
                         .collect(Collectors.toList()));
            vitae.getSectionList()
                 .add(skills);
        } else {
            Supplier<Section> sectionSupplier = (Supplier<Section>) objectMapper
                    .convertValue(request, typeToClazz(request.get("type") + ""));
            if (vitae.getSectionList() == null)
                vitae.setSectionList(new ArrayList<>());
            vitae.getSectionList()
                 .add(sectionSupplier.get());
        }

        return service.update(vitae);
    }

    @PatchMapping
    @IsAuthenticated
    @IsOwned
    public CurriculumVitae update(@Valid @RequestBody Map<String, Object> request) throws UnableToProcess {
        if (!request.containsKey("type"))
            throw new UnableToProcess("Section type not found in request body");
        return null;
    }

    @DeleteMapping("/{username}/{id}")
    @IsAuthenticated
    @IsOwned
    public void removeSection(@PathVariable String username,
                              @PathVariable String id) {
    }

    // ================================================================================================================

    private Class<? extends Supplier<? extends Section>> typeToClazz(String type) throws UnableToProcess {
        return switch (type) {
            case "about" -> AboutRequest.class;
            case "certifications" -> CertificationsRequest.class;
            case "education" -> EducationRequest.class;
            case "intro" -> IntroRequest.class;
            case "languages" -> LanguagesRequest.class;
            case "patents" -> PatentsRequest.class;
            case "projects" -> ProjectsRequest.class;
            case "publications" -> PublicationsRequest.class;
            case "volunteerExperience" -> VolunteerExperienceRequest.class;
            case "workExperience" -> WorkExperienceRequest.class;
            default -> throw new UnableToProcess("The request body type not found");
        };
    }
}
