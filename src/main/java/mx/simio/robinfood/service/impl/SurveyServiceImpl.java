package mx.simio.robinfood.service.impl;

import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import mx.simio.robinfood.dto.SurveyDTO;
import mx.simio.robinfood.entity.Survey;
import mx.simio.robinfood.repository.SurveyRepository;
import mx.simio.robinfood.service.SurveyService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
public class SurveyServiceImpl implements SurveyService {

  private final SurveyRepository surveyRepository;
  private final ModelMapper modelMapper;

  @Autowired
  public SurveyServiceImpl(SurveyRepository surveyRepository,
      ModelMapper modelMapper) {
    this.surveyRepository = surveyRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  public SurveyDTO findSurveyById(Long id) {

    Optional<Survey> theSurvey = surveyRepository.findById(id);

    if (theSurvey.isEmpty()) {
      log.info(":::findSurveyById::: Survey {} not found", id);
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Survey not found");
    }

    return modelMapper.map(theSurvey.get(), SurveyDTO.class);
  }

  @Override
  public SurveyDTO createSurvey(SurveyDTO surveyDTO) {

    Survey survey = modelMapper.map(surveyDTO, Survey.class);
    Survey newSurvey = surveyRepository.save(survey);

    log.info(":::createSurvey::: Survey {} created", newSurvey.getName());

    return modelMapper.map(newSurvey, SurveyDTO.class);
  }
}
