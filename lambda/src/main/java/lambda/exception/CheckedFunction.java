package lambda.exception;

@FunctionalInterface
public interface CheckedFunction<T, R> {

    R apply(T input) throws Throwable;

}
