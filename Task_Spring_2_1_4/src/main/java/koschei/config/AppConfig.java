package koschei.config;

import koschei.models.Egg7;
import koschei.models.Island2;
import koschei.models.Needle8;
import koschei.models.Wood3;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "koschei")
public class AppConfig {

    @Bean
    public static Island2 getIsland(Wood3 wood) {
        return new Island2(wood);
    }
    @Bean
    public static Egg7 getEgg(Needle8 needle) {
        return new Egg7(needle);
    }
}
