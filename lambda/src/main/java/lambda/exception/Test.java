package lambda.exception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @program: java8
 * @version:
 * @description:
 * @author: ling
 * @create: 2021-01-11 10:04
 **/
public class Test {
    public static void main(String[] args) throws IOException {
        long count = Files.walk(Paths.get("D:/Test"))              // 获得项目目录下的所有文件
                          .filter(file -> !Files.isDirectory(file))          // 筛选出文件
                          .filter(file -> file.toString().endsWith(".java")) // 筛选出 java 文件
                          // .flatMap(Attempt.apply(file -> Files.lines(file), ex -> Stream.empty()))
                          .flatMap(Attempt.apply(
                                  file -> Files.lines(file)))        // 将 会抛出受检异常的 Lambda 包装为 抛出非受检异常的 Lambda
                          .filter(line -> !line.trim().isEmpty())            // 过滤掉空行
                          .count();
        System.out.println("代码行数：" + count);

    }
}
