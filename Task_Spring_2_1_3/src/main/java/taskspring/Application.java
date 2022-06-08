package taskspring;

import config.AppConfig;
import model.AnimalsCage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        for (int i = 0; i < 5; i++) {
            AnimalsCage animalsCagebean = applicationContext.getBean(AnimalsCage.class);
            animalsCagebean.whatAnimalSay();
        }
    }
}
