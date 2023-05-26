package ToDoList.demo;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, length = 45)
    private String title;
    @Column(nullable = false, length = 45)
    private LocalDate starting_date;
    @Column(nullable = true, length = 45)
    private LocalDate end_date;
    @Column(nullable = true, length = 45)
    private String file;
    @Column(nullable = false)
    private int done;
    @Column(nullable = true)
    private String datumString;


    public Task(){
        this.title ="";
        this.starting_date = LocalDate.parse("2000-01-01");
        this.end_date=LocalDate.parse("2000-01-01");
        this.file="";
        this.done=0;
        this.datumString="";
    }

    public Task(int id, String title, LocalDate starting_date, LocalDate end_date, String file, int done, String datumString){
        this.id = id;
        this.title = title;
        this.starting_date = starting_date;
        this.end_date = end_date;
        this.file = file;
        this.done = done;
        this.datumString=datumString;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setTitle (String title){
        this.title=title;
    }
    public String getDatumString(){
        return this.datumString;
    }
    public void setDatumString(String datumString){
        this.datumString = datumString;
    }
    public void setStarting_date (LocalDate starting_date){
        this.starting_date = starting_date;
    }

    public void setEnd_date (LocalDate end_date){
        this.end_date=end_date;
    }

    public void setFile (String file){
        this.file=file;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public LocalDate getStarting_date(){
        return this.starting_date;
    }

    public LocalDate getEnd_date(){
        return this.end_date;
    }

    public String getFile(){
        return this.file;
    }

    public void setDone(int done){
        this.done=done;
    }
    public int getDone(){
        return this.done;
    }

    @Override
    public String toString(){
        return "tasks{"+
                "id="+ id + '\'' +
                ", title=" + title+'\'' +
                ", starting_date=" + starting_date +
                ", end_date=" + end_date+
                ", file="+file+'\'' +
                ", done="+done+
                ", datumString="+datumString+'\'' +
                "}";
    }
}