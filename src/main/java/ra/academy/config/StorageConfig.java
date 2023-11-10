package ra.academy.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Configuration
public class StorageConfig {
    private final String serviceAccountKeyPath = "C:\\Users\\hung1\\OneDrive\\Desktop\\upload-firebase\\src\\main\\resources\\firebase-config.json";

    @Bean
    public Storage storage() throws IOException {;
        InputStream serviceAccount = Files.newInputStream(Paths.get(serviceAccountKeyPath));
//        InputStream serviceAccount = new FileInputStream(serviceAccountKeyPath);
        return StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build()
                .getService();
    }
}
