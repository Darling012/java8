package lambda.exception;

@FunctionalInterface
public interface CheckedSupplier<R> {
    R supply() throws Throwable;
}
