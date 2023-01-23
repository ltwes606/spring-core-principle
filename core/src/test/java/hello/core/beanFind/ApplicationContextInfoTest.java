package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "\tbean = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBeans() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) {
                continue;
            }

            Object bean = annotationConfigApplicationContext.getBean(beanDefinitionName);
            System.out.println(
                    "beanDefinitionName = " + beanDefinitionName + "\tbean = " + bean);
        }
    }
}
