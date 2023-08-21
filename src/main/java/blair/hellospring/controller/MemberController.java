package blair.hellospring.controller;

import blair.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
// @Controller
// 스프링이 뜰 때 MemberController로 객체를 생성해서 가지고 있음
// = 스프링 컨테이너에서 스프링 빈이 관리된다!
public class MemberController {

    private final MemberService memberService;

    @Autowired
    // 연결시킬 때 사용
    // MemberController가 생성될 때 Spring bean에 등록되어 있는 MemberService 객체를 가져와서 넣어 준다.
    // 💛이것이 의존성 주입!! Dependency Injection!!
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
