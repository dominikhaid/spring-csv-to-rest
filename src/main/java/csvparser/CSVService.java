package csvparser;

import java.io.IOException;
import java.util.List;
import kbbg.Kbbg;
import kbbg.KbbgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CSVService {

  @Autowired
  KbbgRepository repository;

  public void save(MultipartFile file) {
    try {
      List<Kbbg> kbbgs = CSVHelper.csvToKbbgs(file.getInputStream());
      repository.saveAll(kbbgs);
    } catch (IOException e) {
      throw new RuntimeException("fail to store csv data: " + e.getMessage());
    }
  }

  public List<Kbbg> getAllKbbgs() {
    return repository.findAll();
  }
}
