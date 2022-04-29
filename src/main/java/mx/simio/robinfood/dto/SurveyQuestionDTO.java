package mx.simio.robinfood.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class SurveyQuestionDTO {

  @NotEmpty(message = "Please provide the question")
  private String question;
  private List<AnswerDTO> answers;
}
