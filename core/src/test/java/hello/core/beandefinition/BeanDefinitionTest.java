package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext = new
            AnnotationConfigApplicationContext(AppConfig.class);

    GenericXmlApplicationContext genericXmlApplicationContext = new
            GenericXmlApplicationContext("appConfig.xml");

    @Test
    @DisplayName("Annotation Config 모든 메타 정보")
    void findAnnotationConfigAllBean() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName +
                    " beanDefinition = " + beanDefinition);
        }
    }

    @Test
    @DisplayName("Annotation Config 빈 설정 메타 정보")
    void findAnnotationConfigApplicationBeans() {
        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    annotationConfigApplicationContext.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) {
                continue;
            }
            System.out.println("beanDefinitionName = " + beanDefinitionName +
                    " beanDefinition = " + beanDefinition);
        }
    }

    @Test
    @DisplayName("XML Config 모든 메타 정보")
    void findXmlConfigAllBean() {
        String[] beanDefinitionNames = genericXmlApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    genericXmlApplicationContext.getBeanDefinition(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName +
                    " beanDefinition = " + beanDefinition);
        }
    }

    @Test
    @DisplayName("Xml Config 빈 설정 메타 정보")
    void findXmlConfigApplicationBeans() {
        String[] beanDefinitionNames = genericXmlApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    genericXmlApplicationContext.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) {
                continue;
            }
            System.out.println("beanDefinitionName = " + beanDefinitionName +
                    " beanDefinition = " + beanDefinition);
        }
    }
}
