package web.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /* Метод, указывающий на корневой класс конфигурации типа WebApplicationContext
    для конфигурации уровня приложения */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{DataServiceConfig.class};
    }

    /* Добавление конфигурации класса DispatcherServlet, в которой
    инициализируем ViewResolver, для корректного отображения .jsp */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{WebConfig.class};
    }

    /* Данный метод указывает url, для отображения DispatcherServelt (контекст) */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
