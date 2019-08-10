package stream.MyDemo;

/**
 * @program: java8
 * @description: 基本类型流 文件流
 * @author: Darling
 * @create: 2019-08-10 23:28
 **/

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Java8中还为基本数据类型提供了更直接的流方式，以简化使用。
 * 对于byte，short，int，char，boolean类型可以使用IntStream；
 * 对于long类型可以使用LongStream；
 * 对于float和Double类型可以使用DoubleStream。
 */
public class ElseDemo {
    public static void main(String[] args) {

        IntStream intStream = IntStream.of(1, 2, 3);
        // todo
//        IntStream byteStream = IntStream.
        IntStream rangeStream = IntStream.range(1, 10);  // 不包含上限10
        IntStream rangeClosedStream = IntStream.rangeClosed(1, 10);  // 包含上限10

        // 基本类型流还直接提供了sum, average, max, min等在Stream中并没有的方法。
        System.out.println(intStream.sum());
        System.out.println(intStream.average());
        System.out.println(intStream.min());
        System.out.println(intStream.max());

        // mapToInt/mapToLong/mapToDouble方法把流转成基本类型流


        // 在文件操作中使用流
        try {
            // 访问目录和过滤
            Files.list(Paths.get(".")).forEach(System.out::println);
            Files.list(Paths.get(".")).filter(Files::isDirectory);
            //按扩展名过滤文件
            Files.newDirectoryStream(Paths.get("."), path -> path.toString().endsWith("md")).forEach(System.out::println);
            File[] textFiles = new File(".").listFiles(path -> path.toString().endsWith("txt"));
            //访问子目录
            List<File> allFiles = Stream.of(new File(".").listFiles()).flatMap(file -> file.listFiles() == null ? Stream.of(file) : Stream.of(file.listFiles())).collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
