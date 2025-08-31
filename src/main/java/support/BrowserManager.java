package support;

public class BrowserManager {
    // ThreadLocal to store browser name for each thread
    private static final ThreadLocal<String> browserName = new ThreadLocal<>();

    // Set browser for current thread
    public static void setBrowser(String browser) {
        System.out.println("BrowserManager: Setting browser to " + browser + " for thread " + Thread.currentThread().getId());
        browserName.set(browser);
    }

    // Get browser for current thread
    public static String getBrowser() {
        String browser = browserName.get();
        if (browser == null) {
            browser = "chrome";
            browserName.set(browser);
        }
        System.out.println("BrowserManager: Getting browser " + browser + " for thread " + Thread.currentThread().getId());
        return browser;
    }

    // Clear browser for current thread
    public static void clear() {
        browserName.remove();
    }
}
