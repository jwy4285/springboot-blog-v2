package shop.mtcoding.blogv2.user;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.util.Script;

@Controller
public class UserController {

    @Autowired // DI
    private UserService userService;

    @Autowired
    private HttpSession session;

    // MVC중에 C - V만 받음
    @GetMapping("/joinForm")
    public String joinForm() {
        return "user/joinForm";
    }

    // M - V - C
    @PostMapping("/join")
    public String join(UserRequest.JoinDTO joinDTO) {
        userService.회원가입(joinDTO); // 회원가입 로직을 위임함 서비스한테
        return "user/loginForm"; // persist 초기화
    }

    @GetMapping("/loginForm")
    public String login() {
        return "user/loginForm";
    }

    @PostMapping("/login")
    public @ResponseBody String login(UserRequest.loginDTO loginDto) {
        User sessionUser = userService.로그인(loginDto);
        if (sessionUser == null) {
            return Script.back("로그인실패");
        }
        session.setAttribute("sessionUser", sessionUser);
        return Script.href("/");
    }

}
