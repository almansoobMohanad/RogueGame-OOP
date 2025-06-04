package game.timemanagement;

public class ServiceLocator {
    private static TimeProvider timeProvider;
    public static void registerTimeProvider(TimeProvider provider) {
        if (timeProvider != null) {
            throw new IllegalStateException("TimeProvider already registered");
        }
        timeProvider = provider;
    }

    public static TimeProvider getTimeProvider() {
        return timeProvider;
    }
}
