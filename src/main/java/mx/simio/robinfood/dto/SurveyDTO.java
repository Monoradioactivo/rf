package mx.simio.robinfood.dto;

import java.util.List;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SurveyDTO {

  private Long id;
  @NotEmpty(message = "Name is required")
  private String name;
  @NotEmpty(message = "Description is required")
  private String description;
  @NotNull(message = "At least one question is required")
  private List<SurveyQuestionDTO> questions;
}
