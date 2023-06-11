package app;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Application {

    public static void main(String[] args) {
        String fileName = "src/app/data.txt";
        List<Goal> goals = listFromFile(fileName);
        goals.forEach(System.out::println);
    }
    public static List <Goal> listFromFile(String fileName) {
        List <Goal> goals = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName)){
            int c;
            StringBuilder tmp = new StringBuilder();
            while ((c = fileReader.read()) != -1) {
                if (c == 10) {
                    if (tmp != null) {
                        goals.add(goalFromString(tmp.toString()));
                    }
                    tmp = new StringBuilder();
                } else {
                    tmp.append((char) c);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return goals;
    }
    public static Goal goalFromString(String line) {
        String [] partsOfGoal = line.split("[\\s\t]+");
        return new Goal(partsOfGoal[0], Boolean.valueOf(partsOfGoal[1]), stringToDate(partsOfGoal[2]), stringToDate(partsOfGoal[3]), stringToDate(partsOfGoal[4]));
    }
    public static Date stringToDate(String stringDate) {
        Date date;
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            date = format.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date;
    }
}
class Goal{
    String name;
    boolean status;
    Date beginDate;
    Date deadline;
    Date notify;
    public Goal(String name, boolean status, Date beginDate, Date deadline, Date notify){
        this.name = name;
        this.status = status;
        this.beginDate = beginDate;
        this.deadline = deadline;
        this.notify = notify;
    }
    public Goal (String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getNotify() {
        return notify;
    }

    public void setNotify(Date notify) {
        this.notify = notify;
    }

    @Override
    public String toString() {
        return name + " " + status + " -- " + beginDate + " -- " + deadline + " -- " + notify + " ";
    }
}
