package mx.simio.robinfood.controller;

import javax.validation.Valid;
import mx.simio.robinfood.dto.SurveyDTO;
import mx.simio.robinfood.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.controller.survey-path}")
public class SurveyController {

  private final SurveyService surveyService;

  @Autowired
  public SurveyController(SurveyService surveyService) {
    this.surveyService = surveyService;
  }

  @PostMapping
  public ResponseEntity<SurveyDTO> createSurvey(@Valid @RequestBody SurveyDTO surveyDTO) {
    return new ResponseEntity<>(surveyService.createSurvey(surveyDTO), HttpStatus.CREATED);
  }

  @GetMapping("/{id}")
  public ResponseEntity<SurveyDTO> getSurvey(@PathVariable Long id) {
    return new ResponseEntity<>(surveyService.findSurveyById(id), HttpStatus.OK);
  }
}
