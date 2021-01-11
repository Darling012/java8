package copier;

import lombok.Data;
import lombok.ToString;

import java.util.List;

@ToString
@Data
public class CopyTest1 {
    public String name;
    public InnerClass innerClass;
    public List list;

    @ToString
    @Data
    public static class InnerClass {
        public String InnerName;
    }
}
