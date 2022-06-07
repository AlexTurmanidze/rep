package taskspring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        
        HelloWorld helloWorldBean = (HelloWorld) applicationContext.getBean("helloworld");
        HelloWorld helloWorldBean1 = (HelloWorld) applicationContext.getBean("helloworld");
        
        Cat catBean = (Cat) applicationContext.getBean("cat");
        Cat catBean1 = (Cat) applicationContext.getBean("cat");
        
        System.out.println("helloWorldBean = " + (helloWorldBean == helloWorldBean1));
        System.out.println("catBean = " + (catBean == catBean1));
        
        System.out.println(helloWorldBean.getMessage());
        System.out.println(catBean.getMessage());
    }
}
