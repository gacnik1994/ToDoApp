/*package ToDoList.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.File;



@Controller
public class FileController {


    @GetMapping("/uploadPage")
        public String UploadFileTest(){
            return "uploader";

        }

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file){
        String fileName =file.getOriginalFilename();
        try {
            file.transferTo(new File("C:\\Users\\gacni\\OneDrive\\Documents\\Faks\\3. letnik\\Spletni in mobilni informacijski sistemi\\ToDoApp\\src\\main\\resources\\static\\files\\" + fileName));
        }catch(Exception e){
            return "index";
        }
        return "uploader";
    }
}
*/