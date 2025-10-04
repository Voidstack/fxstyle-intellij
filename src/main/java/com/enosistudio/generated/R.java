package com.enosistudio.generated;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.net.URL;

import com.enosistudio.RFile;
import com.enosistudio.RFolder;
import org.jetbrains.annotations.Contract;

/**
 * Generated resource constants class.
 * Contains hierarchical access to all resource files and folders.
 */
@SuppressWarnings({"java:S101", "unused"})
public final class R {
    private R() {} // Utility class

    
    public static final class com extends RFolder {
        public static final RFolder _self = new com();
        private com() { super("com", "com"); }
        
        public static final class enosistudio extends RFolder {
            public static final RFolder _self = new enosistudio();
            private enosistudio() { super("enosistudio", "com/enosistudio"); }
            
            public static final class fxstyleintellij extends RFolder {
                public static final RFolder _self = new fxstyleintellij();
                private fxstyleintellij() { super("fxstyleintellij", "com/enosistudio/fxstyleintellij"); }
                public static final RFile helloViewFxml = new RFile("com/enosistudio/fxstyleintellij/hello-view.fxml");
                
                public static final class css extends RFolder {
                    public static final RFolder _self = new css();
                    private css() { super("css", "com/enosistudio/fxstyleintellij/css"); }
                    public static final RFile mainCss = new RFile("com/enosistudio/fxstyleintellij/css/main.css");
                    public static final RFile modenaCss = new RFile("com/enosistudio/fxstyleintellij/css/modena.css");
                }
            }
        }
    }
}
