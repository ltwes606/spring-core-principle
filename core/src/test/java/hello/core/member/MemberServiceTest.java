package hello.core.member;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberRepository.save(member);

        // then
        assertThat(memberRepository.findById(1L)).isSameAs(member);
    }
}
