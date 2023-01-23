package hello.core.discount;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;

class DiscountPolicyTest {

    @Test
    void VIPFixDiscount() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        int discountAmount = discountPolicy.discount(member, 5000);

        assertThat(discountAmount).isEqualTo(1000);
    }

    @Test
    void basicFIxDiscount() {
        Member member = new Member(1L, "memberA", Grade.BASIC);

        DiscountPolicy discountPolicy = new FixDiscountPolicy();
        int discountAmount = discountPolicy.discount(member, 5000);

        assertThat(discountAmount).isEqualTo(0);
    }

    @Test
    void VIPRateDiscount() {
        Member member = new Member(1L, "memberA", Grade.VIP);

        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        int discountAmount = discountPolicy.discount(member, 5000);

        assertThat(discountAmount).isEqualTo(500);
    }

    @Test
    void basicRateDiscount() {
        Member member = new Member(1L, "memberA", Grade.BASIC);

        DiscountPolicy discountPolicy = new RateDiscountPolicy();
        int discountAmount = discountPolicy.discount(member, 5000);

        assertThat(discountAmount).isEqualTo(0);
    }
}