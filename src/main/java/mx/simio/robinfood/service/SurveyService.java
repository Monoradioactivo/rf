package mx.simio.robinfood.service;

import mx.simio.robinfood.dto.SurveyDTO;

public interface SurveyService {

  SurveyDTO findSurveyById(Long id);

  SurveyDTO createSurvey(SurveyDTO surveyDTO);
}
