package lambda.exception;

/**
 * @program: java8
 * @version:
 * @description:
 * @author: ling
 * @create: 2021-01-11 10:30
 **/
@FunctionalInterface
public interface CheckedConsumer<T> {

    void accept(T input) throws Throwable;

}
