package lombok;

/**
 * @AllArgsConstructor
 * @NoArgsConstructor
 * @RequiredArgsConstructor
 */
@RequiredArgsConstructor
public class ConstructorTest {

    private final String field1;

    @NonNull
    private final String field2;

    private String field3;

}
