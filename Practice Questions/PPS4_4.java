    import java.util.Scanner;

    abstract class Vehicle {
        private String vehicleType;
        private String model;
        private int year;

        public Vehicle(String vehicleType, String model, int year) {
            this.vehicleType = vehicleType;
            this.model = model;
            this.year = year;
        }

        public abstract void start();

        public static class Car extends Vehicle {
            private String fuelType;

            public Car(String model, int year, String fuelType) {
                super("Car", model, year);
                this.fuelType = fuelType;
            }

            @Override
            public void start() {
                System.out.println("Starting the car engine.");
            }
        }

        public static class Bicycle extends Vehicle {
            private int gearCount;

            public Bicycle(String model, int year, int gearCount) {
                super("Bicycle", model, year);
                this.gearCount = gearCount;
            }

            @Override
            public void start() {
                System.out.println("Pedaling the bicycle.");
            }
        }
    }

    public class PPS4_4 {
        public static void main(String... args) {
            Scanner input = new Scanner(System.in);

            System.out.println("Enter the number of vehicles: ");
            int numberOfVehicles = input.nextInt();
            input.nextLine();

            Vehicle[] vehicles = new Vehicle[numberOfVehicles];

            for (int i = 0; i < numberOfVehicles; i++) {
                System.out.print("Enter vehicle type (car/bicycle): ");
                String vehicleType = input.nextLine();
                System.out.print("Enter model: ");
                String model = input.nextLine();
                System.out.print("Enter year: ");
                int year = input.nextInt();
                input.nextLine();

                if (vehicleType.toLowerCase().equals("car")) {
                    System.out.println("Enter fuel type: ");
                    String fuelType = input.nextLine();
                    vehicles[i] = new Vehicle.Car(model, year, fuelType);
                } else if (vehicleType.toLowerCase().equals("bicycle")) {
                    System.out.println("Enter gear count: ");
                    int gearCount = input.nextInt();
                    input.nextLine();
                    vehicles[i] = new Vehicle.Bicycle(model, year, gearCount);
                } else {
                    System.out.println("Invalid vehicle type.");
                    return;
                }
                System.out.println("");
            }

            for (int i = 0; i < vehicles.length; i++) {
                Vehicle vehicle = vehicles[i];
                vehicle.start();
            }
        }
    }
