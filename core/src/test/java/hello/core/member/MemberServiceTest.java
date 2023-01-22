package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService = new MemberServiceImpl(new MemoryMemberRepository());

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(member.getId());

        // then
        assertThat(findMember).isSameAs(member);
    }

}
