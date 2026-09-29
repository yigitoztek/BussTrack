
package busroutetracker;
import java.util.Scanner;

public class BusRouteTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        System.out.print("Enter the number of stops on the route: ");
        int numStops = scanner.nextInt();
        scanner.nextLine(); 

    
        String[] stopNames = new String[numStops];
        int[] boarding = new int[numStops];
        int[] alighting = new int[numStops];
        int[] occupancy = new int[numStops];

        for (int i = 0; i < numStops; i++) {
            System.out.println("\nStop " + (i + 1));
            System.out.print("Enter stop name: ");
            stopNames[i] = scanner.nextLine();

            System.out.print("Enter passengers boarding: ");
            boarding[i] = scanner.nextInt();

            System.out.print("Enter passengers alighting (getting off): ");
            alighting[i] = scanner.nextInt();
            scanner.nextLine();
        }

        System.out.print("\nEnter the bus's seating capacity: ");
        int seatingCapacity = scanner.nextInt();

        int currentPassengers = 0;
        int overCapacityCount = 0;

        System.out.println("\nROUTE PROGRESSION");
        for (int i = 0; i < numStops; i++) {
            currentPassengers = currentPassengers + boarding[i] - alighting[i];

            if (currentPassengers < 0) {
                System.out.println("Data error at [" + stopNames[i] + "]: cannot have more passengers alighting than are currently on the bus. Occupancy set to 0.");
                currentPassengers = 0;
            }

            occupancy[i] = currentPassengers;
            System.out.println("[" + stopNames[i] + "] Current passengers on bus: " + occupancy[i]);

            if (occupancy[i] > seatingCapacity) {
                System.out.println("Warning: Bus is over capacity at [" + stopNames[i] + "]!");
                overCapacityCount++;
            }
        }

        System.out.println("\n=== ROUTE SUMMARY TABLE ===");
        System.out.println("Stop Name\t\tBoarding\tAlighting\tCurrent Occupancy");
        System.out.println("-----------------------------------------------------------------");
        for (int i = 0; i < numStops; i++) {
            System.out.println(stopNames[i] + "\t\t" + boarding[i] + "\t\t" + alighting[i] + "\t\t" + occupancy[i]);
        }


        int maxBoarding = boarding[0];
        int busiestStopIndex = 0;
        int totalOccupancySum = 0;

        for (int i = 0; i < numStops; i++) {
            if (boarding[i] > maxBoarding) {
                maxBoarding = boarding[i];
                busiestStopIndex = i;
            }
            totalOccupancySum += occupancy[i];
        }


        double averageOccupancy = (double) totalOccupancySum / numStops;

        System.out.println("\nSTATISTICS");
        System.out.println("Busiest stop: " + stopNames[busiestStopIndex] + " (" + maxBoarding + " passengers boarding)");
        System.out.printf("Average occupancy: %.2f passengers%n", averageOccupancy);
        System.out.println("Number of stops exceeding capacity: " + overCapacityCount);


        int finalOccupancy = occupancy[numStops - 1];
        if (finalOccupancy != 0) {
            System.out.println("Warning: " + finalOccupancy + " passengers still on the bus after the final stop — please check your data.");
        } else {
            System.out.println("Route completed successfully. Bus is empty.");
        }

        scanner.close();
    }
}



