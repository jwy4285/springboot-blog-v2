package shop.mtcoding.blogv2.board;

import lombok.Getter;
import lombok.Setter;

public class BoardRequest {

    // 클래스에 내부 클래스 만들어서 관리
    @Getter
    @Setter
    public static class SaveDTO {
        private String title;
        private String content;

    }

}
