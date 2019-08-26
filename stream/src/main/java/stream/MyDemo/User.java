package stream.MyDemo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class User {
    private String id;
    //姓名
    private String name;
    //年龄
    private Integer age;
    //性别
    private Integer sex;
    //所在省市
    private String address;
}
