import java.util.Scanner;

class Innings{
    
    String number;
    String batting_team;
    Long runs;

    public void displayInningsDetails() {
        System.out.println("Innings Details:");
        System.out.println("Inning number: " + number);
        System.out.println("Batting Team: " + batting_team);
        System.out.println("Runs Scored: " + runs);
    }
}

class Course {
    String code;
    String title;
    double totalMarks;

    Course(String code, String title) {
        this.code = code;
        this.title = title;
        this.totalMarks = 0;
    }

    double calculateTotalMarks() {
        return totalMarks;
    }
}

class TheoryCourse {
    Course course;
    double[] testMarks = new double[3];
    double assignmentPresentationMark;
    double[] objectiveTestMarks = new double[2];
    double finalExamMark;

    TheoryCourse(String code, String title) {
        course = new Course(code, title);
    }

    double calculateTotalMarks() {
        double testAvg = (testMarks[0] + testMarks[1] + testMarks[2]) / 3;
        double objectiveTestAvg = (objectiveTestMarks[0] + objectiveTestMarks[1]) / 2;
        course.totalMarks = testAvg + assignmentPresentationMark + objectiveTestAvg + finalExamMark;
        return course.calculateTotalMarks();
    }
}

class LabCourse {
    Course course;
    double[] observationMarks = new double[2];
    double[] individualReportMarks = new double[2];
    double vivaVoiceMark;
    double finalExamMark;

    LabCourse(String code, String title) {
        course = new Course(code, title);
    }

    double calculateTotalMarks() {
        double observationAvg = (observationMarks[0] + observationMarks[1]) / 2;
        double individualReportAvg = (individualReportMarks[0] + individualReportMarks[1]) / 2;
        course.totalMarks = observationAvg + individualReportAvg + vivaVoiceMark + finalExamMark;
        return course.calculateTotalMarks();
    }
}

class Main{
    public static void main(String...args){
        Innings innings = new Innings();
        Scanner input = new Scanner(System.in);
        System.out.println("Innings Details");
        System.out.println("Innings number: ");
        innings.number = input.nextLine();
        System.out.println("Batting Team: ");
        innings.batting_team = input.nextLine();
        System.out.println("Run Scored: ");
        innings.runs = input.nextLong();
        innings.displayInningsDetails();
    }
}