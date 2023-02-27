package stops.calculator.helper;

import stops.calculator.App;

import java.io.InputStream;

public class IOHelper {
    /**
     * Load a file from the classpath
     *
     * @param path file path, the resource root folder is 'src/main/resource'
     * @return the InputStream for the given resource path
     */
    public static InputStream loadClasspathResource(String path) {
        InputStream is = App.class.getClassLoader().getResourceAsStream(path);
        if (is == null) {
            throw new IllegalArgumentException("Resource NOT found in classpath: " + path);
        }
        return is;
    }
}
