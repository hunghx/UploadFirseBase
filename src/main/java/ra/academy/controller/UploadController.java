package ra.academy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ra.academy.service.StorageService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/upload")
public class UploadController {
    @Autowired
    private StorageService storageService;
    @PostMapping("/firebase")
    public String upload(@RequestParam("image") List<MultipartFile> files, Model model){
        // xử lí upload
        if(files.isEmpty()){
            throw  new RuntimeException("file trống");
        }
        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile f: files) {
           String link = storageService.uploadFile(f);
           imageUrls.add(link);
        }
        model.addAttribute("images",imageUrls);
        return "image";
    }
}
