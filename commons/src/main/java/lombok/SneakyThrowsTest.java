package lombok;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @program: java8
 * @version:
 * @description: 受检异常
 * @author: ling
 * @create: 2021-03-30 10:52
 **/
public class SneakyThrowsTest {
     public static void main(String[] args) {
        throwException();
    }
    // @SneakyThrows(value = FileNotFoundException.class)
    @SneakyThrows
    public static void throwException(){
        File file = new File("filePath");
        @Cleanup InputStream inputStream = new FileInputStream(file);
    }
}
