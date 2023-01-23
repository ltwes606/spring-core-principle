package hello.core.scan;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AutoAppConfigTest {

    AnnotationConfigApplicationContext ac = new
            AnnotationConfigApplicationContext(AutoAppConfig.class);

    @Test
    @DisplayName("빈 하위클래스 확인")
    void basicScan() {
        assertThat(ac.getBean(MemberService.class)).isInstanceOf(MemberServiceImpl.class);
        assertThat(ac.getBean(MemberRepository.class)).isInstanceOf(MemoryMemberRepository.class);
        assertThat(ac.getBean(OrderService.class)).isInstanceOf(OrderServiceImpl.class);
        assertThat(ac.getBean(DiscountPolicy.class)).isInstanceOf(FixDiscountPolicy.class);
    }

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("beanDefinitionName = " + beanDefinitionName + "\tbean = " + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력")
    void findApplicationBeans() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition =
                    ac.getBeanDefinition(beanDefinitionName);
            if (beanDefinition.getRole() != BeanDefinition.ROLE_APPLICATION) {
                continue;
            }

            Object bean = ac.getBean(beanDefinitionName);
            System.out.println(
                    "beanDefinitionName = " + beanDefinitionName + "\tbean = " + bean);
        }
    }
}
