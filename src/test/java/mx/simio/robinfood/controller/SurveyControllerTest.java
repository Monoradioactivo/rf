package mx.simio.robinfood.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import mx.simio.robinfood.util.SurveyTestUtil;
import mx.simio.robinfood.dto.SurveyDTO;
import mx.simio.robinfood.entity.Survey;
import mx.simio.robinfood.service.SurveyService;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SurveyController.class)
class SurveyControllerTest {

  @MockBean
  private SurveyService surveyServiceMock;

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private ObjectMapper objectMapper;

  private final ModelMapper modelMapper = new ModelMapper();

  @Test
  void shouldCreateSurvey() throws Exception {
    //Given
    Survey survey = SurveyTestUtil.createSurvey();

    //Then
    mockMvc.perform(post("/api/v1/surveys")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(survey)))
        .andExpect(status().isCreated())
        .andDo(print());
  }

  @Test
  void shouldReturnSurvey() throws Exception {
    //Given
    Long id = 1L;
    Survey survey = SurveyTestUtil.createSurvey();
    survey.setId(id);
    SurveyDTO surveyDTO = modelMapper.map(survey, SurveyDTO.class);

    //When
    when(surveyServiceMock.findSurveyById(id)).thenReturn(surveyDTO);

    //Then
    mockMvc.perform(get("/api/v1/surveys/{id}", id))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(id))
        .andExpect(jsonPath("$.name").value(survey.getName()))
        .andExpect(jsonPath("$.description").value(survey.getDescription()))
        .andExpect(
            jsonPath("$.questions[0].question").value(survey.getQuestions().get(0).getQuestion()));
  }

}
