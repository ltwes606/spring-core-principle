package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public interface MemberService {

    void join(Member member);

    Member findMember(Long memberId);

}
