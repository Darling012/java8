package lambda.exception;
@FunctionalInterface
public interface CheckedBiConsumer<T, U> {

    void accept(T t, U u) throws Throwable;

}
