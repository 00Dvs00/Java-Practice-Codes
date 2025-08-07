import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;

class PPS3_4 {
    public static void main(String...args) throws Exception{
        q1();
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
}
