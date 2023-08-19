package blair.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { // 💛parameter 정보 단축키: command + p
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http에서의 body에 아래 데이터를 직접 넣어주겠다는 의미.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();  // 💛자동 완성 단축키: command + shift + enter
        hello.setName(name);
        return hello; // 객체를 return. HttpMessageConverter -> 객체를 return 하는 경우 json 형식(JsonConverter)을 사용.
    }
    static class Hello {
        private String name;

        // 💛getter/setter 단축키: command + n
        // java bean 규약
        // property 접근 방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
