import java.util.Scanner;
import java.util.Date;
import java.time.Period;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.lang.reflect.Method;

class Institution{
    private String name;
    private String location;

    public Institution(String name, String location) {
        this.name = name;
        this.location = location;
    }

    public static class Department {
        private int ID;
        private String name;
        private String specialization;
        private int maxStudents;
        private int studCount;
        private Student[] s;

        public Department(int ID, String name, String specialization) {
            this.ID = ID;
            this.name = name;
            this.specialization = specialization;
            this.maxStudents = 70;
            this.studCount = 0;
            this.s = new Student[maxStudents];
        }

        public void addStudent(int studentId, String name, int age , String course) {
            if (studCount < maxStudents) {
                s[studCount] = new Student(studentId, name, age, course);
                studCount++;
            } else {
                System.out.println("Department is full, cannot add more students.");
            }
        }

        public void displayStudents() {
            System.out.println("Students in Department " + name + ":");
            for (int i = 0; i < studCount; i++) {
                System.out.println(s[i]);
            }
        }

        public static class Student {
            private int studentID;
            private String name;
            private int age;
            private String course;

            public Student(int studentID, String name, int age, String course) {
                this.studentID = studentID;
                this.name = name;
                this.age = age;
                this.course = course;
            }
        }
    }
}


class PPS3_4 {
    public static void main(String...args) throws Exception{
        Scanner choice = new Scanner(System.in);
        System.out.println("1.Question-1 2.Question-2, 3.Question-3, 4.Question-4, \nEnter the question number you want to solve: ");
        int c = choice.nextInt();
        while(true){
            if (c==1){q1();}
            else if(c==2){q2();}
            else if(c==3){q3();}
            else if(c==4){q4();}
            else if(c>4){break;}
            System.out.println("Enter question number: ");
            c = choice.nextInt();
        }
    }

    public static void q1() throws Exception{
        Scanner input = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Enter start date (dd/mm/yyyy) : ");
        String start = input.next();
        Date sdate = sdf.parse(start);
        System.out.println("Enter end date (dd/mm/yyyy) : ");
        String end = input.next();
        Date edate = sdf.parse(end);
        System.out.println(convert(sdate, edate));
    }

    public static String convert(Date startDate, Date endDate) {
        long dateDifference = endDate.getTime() - startDate.getTime();
        long seconds = dateDifference / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        hours %= 24;
        minutes %= 60;
        seconds %= 60;

        return "Time Difference: " + days + " days, " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds";
    }
    
    public static void q2(){
        Scanner input = new Scanner(System.in);
        System.out.println("Enter your birth date (dd/mm/yyyy) : ");
        String bday = input.next();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate bdate = LocalDate.parse( bday, dateFormat);
        LocalDate currentTime = LocalDate.now();
        
        Period period = Period.between(bdate,currentTime);
        int years = period.getYears();
        int months = period.getMonths();
        int days = period.getDays();
        
        System.out.println("You are " + years + " years, " + months + " months, and " + days + " days old.");
    }
    
    public static void q3(){
        LocalDate startDate = LocalDate.of(2020,1,1);
        LocalDate endDate = LocalDate.of(2020,12,31);
        
        double start = startDate.toEpochDay();
        double end = endDate.toEpochDay();
        double random = Math.random();
        long randomday = (long) (start + (Math.random() * (end - start) ));
        LocalDate randomDate = LocalDate.ofEpochDay(randomday);

        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = randomDate.format(dateFormat.ofPattern("dd/MM/yyyy"));
        System.out.println("Random Date: " + formattedDate);
    }
    
    public static void q4() throws Exception{
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Institute Name: ");
        String name = input.nextLine();
        System.out.println("Enter Institute Location: ");
        String location = input.nextLine();

        System.out.println("Enter Department ID: ");
        int Dept_id = input.nextInt();
        System.out.println("Enter department Name: ");
        String dept_name = input.nextLine();
        System.out.println("Enter Department Specialization: ");
        String spec = input.nextLine();

        Class c = Class.forName("Institution");
        Institution I = (Institution) c.newInstance();
        Method m_i = c.getDeclaredMethod("Institution",name,location);
        m_i.setAccessible(true);
        m_i.invoke(I,name,location);

        Department D = (Department) c.newInstance();
        Method m_d = c.getDeclaredMethod("Department",Dept_id,dept_name,spec);
        m_d.setAccessible(true);
        m_d.invoke(D,Dept_id,dept_name,spec);

        System.out.println("Enter Student ID: ");
        int stud_ID = input.nextInt();
        System.out.println("Enter Student Name: ");
        String stud_name = input.nextLine();
        System.out.println("Enter Student age: ");
        int age = input.nextInt();
        System.out.println("Enter Student course: ");
        String course = input.nextLine();

        Method m_s = c.getDeclaredMethod("addStudent", stud_ID,stud_name,age,course);
        m_s.setAccessible(true);
        m_s.invoke(D,stud_ID,stud_name,age,course);

        Method m = c.getDeclaredMethod("displayStudents", null);
        m.setAccessible(true);
        m.invoke(D,null);
    }
}
