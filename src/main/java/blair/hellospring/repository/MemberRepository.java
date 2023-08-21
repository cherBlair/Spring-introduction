package blair.hellospring.repository;

import blair.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {

    Member save(Member member);
    Optional<Member> findById(Long id); // Optional: java8 -> null 반환을 감싸서 반환하는 형식
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

