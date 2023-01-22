package order;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

class OrderServiceTest {

    @Test
    void createOrder() {
        //given
        long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());
        memberService.join(member);

        OrderService orderService = new OrderServiceImpl(
                new MemoryMemberRepository(), new FixDiscountPolicy());

        // when
        Order order = orderService.createOrder(memberId, "itemA", 5000);

        // then
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
        assertThat(order.calculatePrice()).isEqualTo(4000);
    }
}