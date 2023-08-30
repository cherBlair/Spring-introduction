package blair.hellospring.service;

import blair.hellospring.domain.Member;
import blair.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//@Service
@Transactional
public class MemberService {
    // 💛class Test 생성 단축키(⭐️⭐️⭐️⭐️⭐) -> command + shift + t
    private final MemberRepository memberRepository;

    // memberRepository를 new로 생성하는 것이 아니라 외부에서 넣어주도록 아래와같이 코드 수정
    // 의존성 주입 (Dependency Injection⭐️⭐️⭐️⭐️⭐️)
    //@Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원 금지
//        Optional<Member> result = memberRepository.findByName(member.getName()); // 💛return 단축키: command + option + v
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });
        // -> 위 코드 리팩토링
//        memberRepository.findByName(member.getName())
//                .ifPresent(m -> {
//                    throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });

        // -> 위 코드를 또 한 번 리팩토링
        // -> 단축키 control + t -> 메소드로 빼주기 💛-> method 검색 -> extract method 선택

        long start = System.currentTimeMillis();

        try {
            validateDuplicatedMember(member); // 중복 회원 검증
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join " + timeMs + "ms");
        }
    }

    /**
     *전체 회원 조회
     */
    public List<Member> findMembers() {
        long start = System.currentTimeMillis();
        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers " + timeMs + "ms");
        }
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void validateDuplicatedMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
}
