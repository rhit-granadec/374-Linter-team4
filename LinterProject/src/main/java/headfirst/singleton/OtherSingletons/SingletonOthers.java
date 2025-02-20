package headfirst.singleton.OtherSingletons;
//
//
//
//
//public class Configuration {
//    private static Configuration instance = new Configuration();
//    private String setting1;
//    private String setting2;
//
//    private Configuration() {
//        // Load configurations
//    }
//
//    public static Configuration getInstance() {
//        return instance;
//    }
//
//    public String getSetting1() {
//        return setting1;
//    }
//
//    public String getSetting2() {
//        return setting2;
//    }
//}
//
//// In other classes
//public class SomeClass {
//    public void doSomething() {
//        String config1 = Configuration.getInstance().getSetting1();
//        // Use config1
//    }
//}
//
//
//public class Database {
//    private static Database instance = new Database();
//
//    private Database() {
//        // Initialize connection
//    }
//
//    public static Database getInstance() {
//        return instance;
//    }
//
//    public void query(String sql) {
//        // Execute query
//    }
//}
//
//public class DataProcessor {
//    public void processData() {
//        Database.getInstance().query("SELECT * FROM data");
//    }
//}
//
//
//public class Logger {
//    private static Logger instance = new Logger();
//
//    private Logger() {}
//
//    public static Logger getInstance() {
//        return instance;
//    }
//
//    public void log(String message) {
//        // Log message
//    }
//}
//
//public class MyClass {
//    public void doSomething() {
//        // Some logic
//        Logger.getInstance().log("Doing something");
//    }
//}
//
//
//public class Counter {
//    private static Counter instance;
//    private int count = 0;
//
//    private Counter() {}
//
//    public static Counter getInstance() {
//        if (instance == null) {
//            instance = new Counter();
//        }
//        return instance;
//    }
//
//    public void increment() {
//        count++;
//    }
//
//    public int getCount() {
//        return count;
//    }
//}
//
//
//public class SystemManager {
//    private static SystemManager instance = new SystemManager();
//
//    private SystemManager() {}
//
//    public static SystemManager getInstance() {
//        return instance;
//    }
//
//    public String getConfig(String key) {
//        // Get config
//        return "";
//    }
//
//    public void executeQuery(String sql) {
//        // Execute query
//    }
//}
//
