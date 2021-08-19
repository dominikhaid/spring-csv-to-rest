package kbbg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "kbbg", "csvparser" })
public class KbbgApplication {

  public static void main(String[] args) throws Throwable {
    SpringApplication.run(KbbgApplication.class, args);
  }
}
