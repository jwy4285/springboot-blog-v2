package shop.mtcoding.blogv2.reply;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {
    
    @PostMapping("/reply/save")
    public String save() {

        return "reply/save";
    }
}
