package mx.simio.robinfood.service.impl;

import java.util.Optional;
import mx.simio.robinfood.util.SurveyTestUtil;
import mx.simio.robinfood.dto.SurveyDTO;
import mx.simio.robinfood.entity.Survey;
import mx.simio.robinfood.repository.SurveyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.web.server.ResponseStatusException;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SurveyServiceImplTest {

  private ModelMapper modelMapper;
  private SurveyServiceImpl surveyServiceSUT;
  private SurveyRepository surveyRepositoryMock;

  @BeforeEach
  void setUp() {
    surveyRepositoryMock = mock(SurveyRepository.class);
    modelMapper = new ModelMapper();
    surveyServiceSUT = new SurveyServiceImpl(surveyRepositoryMock, modelMapper);
  }

  @Test
  void shouldFindSurveyById() {
    //Given
    Long id = 1L;
    Optional<Survey> surveyOptional = Optional.of(SurveyTestUtil.createSurvey());
    surveyOptional.get().setId(id);

    //When
    when(surveyRepositoryMock.findById(id)).thenReturn(surveyOptional);
    SurveyDTO result = surveyServiceSUT.findSurveyById(id);

    //Then
    assertNotNull(result);
    assertEquals(id, result.getId());
    assertEquals(surveyOptional.get().getName(), result.getName());
    assertEquals(surveyOptional.get().getDescription(), result.getDescription());
    assertEquals(surveyOptional.get().getQuestions().size(), result.getQuestions().size());
    verify(surveyRepositoryMock, times(1)).findById(id);
  }

  @Test
  void shouldThrowExceptionWhenSurveyNotFound() {
    //Given
    Long id = 1L;
    Optional<Survey> surveyOptional = Optional.empty();

    //When
    when(surveyRepositoryMock.findById(id)).thenReturn(surveyOptional);

    //Then
    assertThrows(ResponseStatusException.class, () -> surveyServiceSUT.findSurveyById(id));
    verify(surveyRepositoryMock, times(1)).findById(id);
  }

  @Test
  void shouldCreateSurvey() {
    //Given
    Long id = 1L;
    Survey survey = SurveyTestUtil.createSurvey();
    SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);
    surveyDTO.setId(id);

    //When
    when(surveyRepositoryMock.save(any(Survey.class))).thenReturn(survey);
    SurveyDTO result = surveyServiceSUT.createSurvey(surveyDTO);

    //Then
    assertNotNull(result);
    assertEquals(surveyDTO.getName(), result.getName());
    assertEquals(surveyDTO.getDescription(), result.getDescription());
    assertEquals(surveyDTO.getQuestions().size(), result.getQuestions().size());
    verify(surveyRepositoryMock, times(1)).save(any(Survey.class));
  }
}
