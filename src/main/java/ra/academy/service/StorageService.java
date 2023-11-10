package ra.academy.service;

import com.google.cloud.storage.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class StorageService {
    @Autowired
    private Storage storage;
    private final String bucketName = "fir-upload-firebase-96e90.appspot.com"; // tên bucket
    private String pathUpload = "C:\\Users\\hung1\\OneDrive\\Desktop\\upload-firebase\\src\\main\\webapp\\uploads\\";

    public String uploadFile(MultipartFile file){
        File uploadFolder = new File(pathUpload);
        if (!uploadFolder.exists()){
            uploadFolder.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        File fileUpload = new File(uploadFolder+File.separator+fileName);

        try {
            FileCopyUtils.copy(file.getBytes(), fileUpload);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return uploadFileToFirebaseStorage(storage,uploadFolder+File.separator+fileName,bucketName);
    }
    private String uploadFileToFirebaseStorage(Storage storage, String localFilePath, String bucketName)  {
        Path localPath = Paths.get(localFilePath);
        String fileName = localPath.getFileName().toString();

        BlobId blobId = BlobId.of(bucketName, fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        // Thiết lập quyền truy cập công cộng
        List<Acl> acls = new ArrayList<>();
        acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));
        blobInfo = blobInfo.toBuilder().setAcl(acls).build();
        try {
            Blob blob = storage.create(blobInfo, Files.readAllBytes(localPath));
            return blob.getMediaLink();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
