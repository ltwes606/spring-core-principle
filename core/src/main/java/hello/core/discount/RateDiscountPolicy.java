package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

@Component
@hello.core.annotation.RateDiscountPolicy
public class RateDiscountPolicy implements DiscountPolicy {

    // 할인율
    private double discountRate = 0.1;

    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return (int) (price * discountRate);
        }
        return 0;
    }
}
