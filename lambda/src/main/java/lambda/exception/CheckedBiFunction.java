package lambda.exception;
@FunctionalInterface
public interface CheckedBiFunction<T, U, R> {

    R apply(T t, U u) throws Throwable;

}
