package mx.simio.robinfood.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AnswerDTO {

  @NotEmpty(message = "Please provide an answer")
  private String value;
}
