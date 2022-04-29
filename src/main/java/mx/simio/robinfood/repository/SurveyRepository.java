package mx.simio.robinfood.repository;

import java.util.Optional;
import mx.simio.robinfood.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

  Optional<Survey> findById(Long id);
}
