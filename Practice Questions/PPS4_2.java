import java.util.Scanner;

class CEO {
    double base_salary;
    String skill;

    CEO(double base_salary, String skill) {
        this.base_salary = base_salary;
        this.skill = skill;
    }

    public boolean NotSkilled() {
        String lowercaseskill = skill.toLowerCase();
        return lowercaseskill.equals("no");
    }

    class Managers {
        int Experience;

        Managers(double base_salary, String skill, int Experience) {
            CEO.this.base_salary = base_salary; 
            CEO.this.skill = skill;
            this.Experience = Experience;
        }

        public double calculateSalary() {
            double salary;
            double bonus = (Experience >= 10.0) ? (0.1 * CEO.this.base_salary) :
                    (Experience >= 7.5 && Experience < 10.0) ? (0.08 * CEO.this.base_salary) :
                    (Experience >= 5.0 && Experience < 7.5) ? (0.06 * CEO.this.base_salary) :
                    (Experience >= 3.0 && Experience < 5.0) ? (0.03 * CEO.this.base_salary) : 0;

            if (NotSkilled()) {
                salary = CEO.this.base_salary + bonus;
            } else {
                salary = CEO.this.base_salary + bonus + (0.02 * CEO.this.base_salary);
            }

            return salary;
        }
    }

    class Supervisors {
        int supervised;

        Supervisors(double base_salary, String skill, int supervised) {
            CEO.this.base_salary = base_salary; 
            CEO.this.skill = skill; 
            this.supervised = supervised;
        }

        public double calculateSalary() {
            double salary;
            double bonus = (supervised >= 10) ? (0.1 * CEO.this.base_salary) :
                    (supervised >= 8.0 && supervised < 10.0) ? (0.08 * CEO.this.base_salary) :
                    (supervised >= 5.0 && supervised < 8.0) ? (0.06 * CEO.this.base_salary) :
                    (supervised >= 3.0 && supervised < 5.0) ? (0.03 * CEO.this.base_salary) : 0;

            if (NotSkilled()) {
                salary = CEO.this.base_salary + bonus;
            } else {
                salary = CEO.this.base_salary + bonus + (0.02 * CEO.this.base_salary);
            }

            return salary;
        }
    }
}

public class PPS4_2 {
    public static void main(String... args) {
        String name;
        String designation;
        double base_salary;
        Scanner input = new Scanner(System.in);
        System.out.println("Name of Employee: ");
        name = input.nextLine();
        System.out.println("Designation of Employee: ");
        designation = input.nextLine();
        System.out.println("Enter Base Salary: ");
        base_salary = input.nextDouble();

        if (designation.toLowerCase().equals("ceo")) {
            CEO obj = new CEO(base_salary, "");
            System.out.println("Total Salary: " + obj.base_salary);
        } else if (designation.toLowerCase().equals("manager")) {
            System.out.println("Experience: ");
            int experience = input.nextInt();
            input.nextLine();
            System.out.println("Additional Skill: ");
            String skill = input.nextLine();
            CEO.Managers obj1 = new CEO(base_salary, "").new Managers(base_salary, skill, experience);
            double sal = obj1.calculateSalary();
            System.out.println("Bonus % for Additional Skills: " + (0.02 / 100) * sal);
            System.out.println("Total Salary: " + sal);
        } else if (designation.toLowerCase().equals("supervisor")) {
            System.out.println("Number of Employees Supervised: ");
            int supervised = input.nextInt();
            input.nextLine(); 
            System.out.println("Additional Skill: ");
            String skill = input.nextLine();
            CEO.Supervisors obj2 = new CEO(base_salary, "").new Supervisors(base_salary, skill, supervised);
            double sal = obj2.calculateSalary();
            System.out.println("Bonus % for Additional Skills: " + (0.02 / 100) * sal);
            System.out.println("Total Salary: " + sal);
        }
    }
}
