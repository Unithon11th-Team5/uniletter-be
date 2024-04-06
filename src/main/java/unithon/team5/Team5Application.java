package unithon.team5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Team5Application {

  public static void main(String[] args) {
    System.out.println("test");
    SpringApplication.run(Team5Application.class, args);
  }
}
