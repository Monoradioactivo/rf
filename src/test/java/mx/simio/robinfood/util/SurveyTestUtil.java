package mx.simio.robinfood.util;

import java.util.List;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mx.simio.robinfood.entity.Answer;
import mx.simio.robinfood.entity.Survey;
import mx.simio.robinfood.entity.SurveyQuestion;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SurveyTestUtil {

  public static Survey createSurvey() {
    Answer answer = new Answer();
    Survey survey = new Survey();
    SurveyQuestion surveyQuestion = new SurveyQuestion();

    answer.setValue("Yes");

    surveyQuestion.setQuestion("Is this a test?");
    surveyQuestion.setAnswers(List.of(answer));

    survey.setName("Test");
    survey.setDescription("The Description");
    survey.setQuestions(List.of(surveyQuestion));

    return survey;
  }
}
