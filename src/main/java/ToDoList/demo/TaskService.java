package ToDoList.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired    private TaskRepository repo;

    public List<Task> listAll() {
        return (List<Task>) repo.findAll();}

    public void save(Task task){repo.save(task);}

    public Task get(int id) throws TaskNotFoundException{
        Optional<Task> byId = repo.findById(id);
        if(byId.isPresent()){
            return byId.get();
        }
        throw new TaskNotFoundException("Ni opravila z zeljenim id: " + id);
    }

    public void delete(int id){
        repo.deleteById(id);
    }

    public void upload(MultipartFile file) throws IOException {
                    file.transferTo(new File("C:\\Users\\gacni\\OneDrive\\Documents\\Faks\\3. letnik\\Spletni in mobilni informacijski sistemi\\ToDoApp\\src\\main\\resources\\static\\files\\" + file.getOriginalFilename()));

    }
}
