package kbbg;

import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KbbgRepository extends JpaRepository<Kbbg, Long> {
  List<Kbbg> findByRl100CodeContaining(String l100Short, Sort sort);
}
