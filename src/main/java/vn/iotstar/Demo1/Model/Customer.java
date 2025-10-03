package vn.iotstar.Demo1.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

//Cho phép sử dụng pattern builder để tạo đối tượng một cách linh hoạt
@Builder
public class Customer {
    private String id;
    private String name;
    private String phoneNumber;
    private String email;
}