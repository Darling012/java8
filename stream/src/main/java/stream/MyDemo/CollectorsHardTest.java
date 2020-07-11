package stream.MyDemo;

import java.util.List;

public class CollectorsHardTest {
    // 怎么解决一个流中处理多个属性问题
    public static void main(String[] args) {
        List<User> list = Data.getData();
        // Map<String,List<User>> mapAge =
        //         list.stream()
        //         .collect(Collectors.groupingBy(User::getName,Collectors.mapping(Function.identity(),Collectors.toCollection(::new))));
        // MapUtils.verbosePrint(System.out, "年龄和", mapAge);


        // Map<String, Integer> mapSex = list.stream()
        //         .collect(Collectors.groupingBy(User::getName, Collectors.summingInt(User::getSex)));
        //
        // MapUtils.verbosePrint(System.out, "性别和", mapSex);

        //       list.stream().collect(Collectors.groupingBy(User::getName,Collectors.groupingBy()));

        // Map<String, List<User>> nameMap = list.stream().collect(Collectors.groupingBy(User::getName));
        // MapUtils.verbosePrint(System.out, "1", nameMap);
    }
}
