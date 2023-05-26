package ToDoList.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Controller
public class TaskController {
        String fileName = "";
        @Autowired private TaskService service;

        @GetMapping("/home")

        public String prikaziOpravila(Model model){
                List<Task> listTasks = service.listAll();
                List<Task> pendingTasks = new ArrayList<Task>();
                List<Task> doneTasks = new ArrayList<Task>();
                String test2 ="";
                for(int i=0;i<listTasks.size();i++){
                        int j = listTasks.get(i).getDone();
                        if(j == 0) {
                                pendingTasks.add(listTasks.get(i));
                        }else{
                                doneTasks.add(listTasks.get(i));
                        }
                }
                int[] idTasks = new int[listTasks.size()];
                int[] pendingId = new int[pendingTasks.size()];
                int[] doneId = new int[doneTasks.size()];
                for(int i=0; i<listTasks.size();i++){
                        idTasks[i]=listTasks.get(i).getId();
                }
                for(int i=0; i<pendingTasks.size();i++){
                        pendingId[i]=pendingTasks.get(i).getId();
                }
                for(int i=0; i<doneTasks.size();i++){
                        doneId[i]=doneTasks.get(i).getId();
                }
                model.addAttribute("listTasks", listTasks);
                model.addAttribute("idTasks", idTasks);
                model.addAttribute("pendingTasks", pendingTasks);
                model.addAttribute("doneTasks", doneTasks);
                model.addAttribute("pendingId",pendingId);
                model.addAttribute("doneId",doneId);
                LocalDate now = LocalDate.now();
                DateTimeFormatter myFormat = DateTimeFormatter.ofPattern("dd MMMM yyyy");
                String formatDate = now.format(myFormat);
                model.addAttribute("formatDate",formatDate);
                model.addAttribute("task",new Task());
                model.addAttribute("localDate", LocalDate.now());
                return "index";
        }
        @GetMapping("/uploader")
        public String getUploadSite(){
                return "uploader";
        }

        @PostMapping("/upload")
        public String Upload(@RequestParam("file") MultipartFile file){
                fileName =file.getOriginalFilename();
                try {
                        file.transferTo(new File("C:\\Users\\gacni\\OneDrive\\Documents\\Faks\\3. letnik\\Spletni in mobilni informacijski sistemi\\ToDoApp\\src\\main\\resources\\static\\files\\" + fileName));
                }catch(Exception e){
                        return "index";
                }
                return "uploader";
        }

        @PostMapping("/home/new")
        public String saveTask(Task task, RedirectAttributes ra ) {
                //set current date when the task is created
                LocalDate ld = LocalDate.now();
                task.setStarting_date(ld);

                //transform end date from string to date.
                String testDate = task.getDatumString();
                if(testDate != "") {
                        //get day
                        String day = Character.toString(testDate.charAt(4)) + Character.toString(testDate.charAt(5));
                        //get year
                        String year = Character.toString(testDate.charAt(8)) +
                                Character.toString(testDate.charAt(9)) +
                                Character.toString(testDate.charAt(10)) +
                                Character.toString(testDate.charAt(11));
                        //get month
                        String month = "";
                        if (testDate.contains("Jan")) {
                                month = "01";
                        } else if (testDate.contains("Feb")) {
                                month = "02";
                        } else if (testDate.contains("Mar")) {
                                month = "03";
                        } else if (testDate.contains("Apr")) {
                                month = "04";
                        } else if (testDate.contains("May")) {
                                month = "05";
                        } else if (testDate.contains("Jun")) {
                                month = "06";
                        } else if (testDate.contains("Jul")) {
                                month = "07";
                        } else if (testDate.contains("Aug")) {
                                month = "08";
                        } else if (testDate.contains("Sep")) {
                                month = "09";
                        } else if (testDate.contains("Oct")) {
                                month = "10";
                        } else if (testDate.contains("Nov")) {
                                month = "11";
                        } else if (testDate.contains("Dec")) {
                                month = "12";
                        } else {
                                month = "00";
                        }
                        //get final date and transform it to date format
                        String endDate = year + "-" + month + "-" + day;
                        task.setEnd_date(LocalDate.parse(endDate));
                }

                //set task as not done always
                task.setDone(0);
                if(fileName != "") {
                        task.setFile("/files/" + fileName);
                }
                service.save(task);
                return "redirect:/home";
        }


        @GetMapping("/home/{id}")
        public String editTask(@PathVariable("id") int id, Model model, RedirectAttributes ra){
                try{
                        Task task = service.get(id);
                        model.addAttribute("task", task);
                        return "addTask";

                }catch(TaskNotFoundException e){
                        ra.addFlashAttribute("message", e.getMessage());
                        return "redirect:/home";
                }

        }
        @GetMapping("izbrisi/{id}")
        public String deleteTask(@PathVariable("id") int id, RedirectAttributes ra){
                //try {
                service.delete(id);
                ra.addFlashAttribute("message","Taks with Id: "+ id + " deleted!");
                //}catch (VoznikNotFoundException e){
                //  ra.addFlashAttribute("message", e.getMessage());
                //}
                return "redirect:/home";
        }
        @GetMapping("/done/{id}")
        public String doneTask(@PathVariable("id") int id, Model model, RedirectAttributes ra){
                try{
                        Task task = service.get(id);
                        if(task.getDone() == 0) {
                                task.setDone(1);
                        }else {
                                task.setDone(0);
                        }
                        service.save(task);
                        return "redirect:/home";

                }catch(TaskNotFoundException e){
                        ra.addFlashAttribute("message", e.getMessage());
                        return "redirect:/home";
                }

        }
}
