package lombok;

/**
 * @NonNull注解
 * 生成非空检查
 */
public class NonNullTest {

    public NonNullTest(@NonNull String field) {
        System.out.println(field);
    }

}
