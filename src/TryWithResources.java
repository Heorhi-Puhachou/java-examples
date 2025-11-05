import java.io.IOException;

public class TryWithResources {

    class MyResource implements AutoCloseable {

        @Override
        public void close() throws IOException {
            throw new IOException("Error in close");
        }
    }

    private void testOld() throws IOException {
        try {
            throw new IOException("Error in try");
        } finally {
            throw new IOException("Error in finally");
        }
    }

    private void testTryWithResources() throws IOException {
        try (MyResource r = new MyResource()) {
            throw new IOException("Error in try");
        }
    }

    public static void main(String... args) throws IOException {
        TryWithResources test = new TryWithResources();

        // The original exception is lost, replaced by the close/finally exception.
        //test.testOld();

        // You keep the main cause, and the cleanup error is preserved as suppressed.
        test.testTryWithResources();
    }
}
