import java.util.*;

class AddOnService {

    private String serviceName;
    private double cost;

    public AddOnService(String serviceName, double cost) {
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public String getServiceName() {
        return serviceName;
    }

    public double getCost() {
        return cost;
    }
}

class AddOnServiceManager {

    private Map<String, List<AddOnService>> servicesByReservation;

    public AddOnServiceManager() {
        servicesByReservation = new HashMap<>();
    }

    public void addService(String reservationId, AddOnService service) {

        servicesByReservation
                .computeIfAbsent(reservationId, k -> new ArrayList<>())
                .add(service);
    }

    public double calculateTotalServiceCost(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) return 0;

        double total = 0;

        for (AddOnService s : services) {
            total += s.getCost();
        }

        return total;
    }

    public void displayServices(String reservationId) {

        List<AddOnService> services = servicesByReservation.get(reservationId);

        if (services == null) {
            System.out.println("No services added.");
            return;
        }

        for (AddOnService s : services) {
            System.out.println(s.getServiceName() + " - " + s.getCost());
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("Add-On Service Selection\n");

        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        AddOnService s1 = new AddOnService("Breakfast", 200);
        AddOnService s2 = new AddOnService("Airport Pickup", 500);
        AddOnService s3 = new AddOnService("Extra Bed", 300);

        manager.addService(reservationId, s1);
        manager.addService(reservationId, s2);
        manager.addService(reservationId, s3);

        System.out.println("Services for Reservation " + reservationId + ":");
        manager.displayServices(reservationId);

        double total = manager.calculateTotalServiceCost(reservationId);

        System.out.println("\nTotal Add-On Cost: " + total);
    }
}