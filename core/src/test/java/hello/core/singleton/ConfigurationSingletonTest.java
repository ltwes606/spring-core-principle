package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class ConfigurationSingletonTest {

    @Test
    void configurationSingletonTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        TestMemberServiceImpl memberService = ac.getBean("memberService",
                TestMemberServiceImpl.class);
        TestOrderServiceImpl orderService = ac.getBean("orderService", TestOrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        //모두 같은 인스턴스를 참고하고 있다.
        System.out.println(
                "memberService -> memberRepository = " + memberService.getMemberRepository());
        System.out.println(
                "orderService -> memberRepository  = " + orderService.getMemberRepository());
        System.out.println("memberRepository = " + memberRepository);

        assertThat(memberService.getMemberRepository()).isSameAs(memberRepository);
        assertThat(orderService.getMemberRepository()).isSameAs(memberRepository);
    }

    @Configuration
    static class TestConfig {

        // Test memberService Class
        @Bean
        public MemberService memberService() {
            System.out.println("call AppConfig.memberService");
            return new TestMemberServiceImpl(memberRepository());
        }

        @Bean
        public MemberRepository memberRepository() {
            System.out.println("call AppConfig.memberRepository");
            return new MemoryMemberRepository();
        }

        // Test OrderService Class
        @Bean
        public OrderService orderService() {
            System.out.println("call AppConfig.orderService");
            return new TestOrderServiceImpl(memberRepository(), discountPolicy());
        }

        @Bean
        public DiscountPolicy discountPolicy() {
            return new FixDiscountPolicy();
        }
    }

    static class TestMemberServiceImpl implements MemberService {

        private final MemberRepository memberRepository;

        public TestMemberServiceImpl(MemberRepository memberRepository) {
            this.memberRepository = memberRepository;
        }

        @Override
        public void join(Member member) {
        }

        @Override
        public Member findMember(Long memberId) {
            return null;
        }

        // Test Method
        public MemberRepository getMemberRepository() {
            return memberRepository;
        }
    }

    static class TestOrderServiceImpl implements OrderService {

        private final MemberRepository memberRepository;
        private final DiscountPolicy discountPolicy;

        public TestOrderServiceImpl(MemberRepository memberRepository,
                DiscountPolicy discountPolicy) {
            this.memberRepository = memberRepository;
            this.discountPolicy = discountPolicy;
        }

        @Override
        public Order createOrder(Long memberId, String itemName, int itemPrice) {
            return null;
        }

        // Test Method
        public MemberRepository getMemberRepository() {
            return memberRepository;
        }
    }
}
