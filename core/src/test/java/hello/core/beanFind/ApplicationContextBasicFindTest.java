package hello.core.beanFind;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextBasicFindTest {

    AnnotationConfigApplicationContext annotationConfigApplicationContext
            = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈 이름만으로 조회")
    void findBeanByName() {
        Object memberService = annotationConfigApplicationContext
                .getBean("memberService");

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("타입만으로 조회")
    void findBeanByType() {
        Object memberService = annotationConfigApplicationContext
                .getBean(MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회 성공")
    void findBeanByNameAndTypeSuccess() {
        Object memberService = annotationConfigApplicationContext
                .getBean("memberService", MemberService.class);

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
    }

    @Test
    @DisplayName("구체 타입으로 조회 실패")
    void findBeanByNameAndTypeFail() {
        Assertions.assertThrows(NoSuchBeanDefinitionException.class, () ->
                annotationConfigApplicationContext.getBean("xxxx", MemberService.class));
    }
}
