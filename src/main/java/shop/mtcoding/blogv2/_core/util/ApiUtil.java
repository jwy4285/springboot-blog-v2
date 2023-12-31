package shop.mtcoding.blogv2._core.util;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ApiUtil<T> {
    private boolean sucuess; //true
    private T data; // T인이유 제네릭 쓸거라서 데이터에는 댓글쓰기 성공
    
    //공통 DTO
    public ApiUtil(boolean sucuess, T data) {
        this.sucuess = sucuess;
        this.data = data;
    }

    
}
