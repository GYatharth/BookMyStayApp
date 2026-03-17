import java.io.*;
import java.util.*;

class RoomInventory {

    private Map<String, Integer> availability;

    public RoomInventory() {
        availability = new HashMap<>();
        availability.put("Single", 5);
        availability.put("Double", 3);
        availability.put("Suite", 2);
    }

    public Map<String, Integer> getAvailability() {
        return availability;
    }

    public void setAvailability(String type, int count) {
        availability.put(type, count);
    }
}

class FilePersistenceService {

    public void saveInventory(RoomInventory inventory, String filePath) {

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {

            for (String type : inventory.getAvailability().keySet()) {
                int count = inventory.getAvailability().get(type);
                writer.write(type + "=" + count);
                writer.newLine();
            }

            System.out.println("Inventory saved successfully.");

        } catch (IOException e) {
            System.out.println("Error saving inventory.");
        }
    }

    public void loadInventory(RoomInventory inventory, String filePath) {

        File file = new File(filePath);

        if (!file.exists()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] parts = line.split("=");

                if (parts.length == 2) {
                    String type = parts[0];
                    int count = Integer.parseInt(parts[1]);
                    inventory.setAvailability(type, count);
                }
            }

            System.out.println("Inventory loaded successfully.");

        } catch (Exception e) {
            System.out.println("Error reading inventory file. Starting fresh.");
        }
    }
}

public class BookMyStayApp {

    public static void main(String[] args) {

        System.out.println("System Recovery");

        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistence = new FilePersistenceService();

        String filePath = "inventory.txt";

        persistence.loadInventory(inventory, filePath);

        System.out.println("\nCurrent Inventory:");
        for (String type : inventory.getAvailability().keySet()) {
            System.out.println(type + ": " + inventory.getAvailability().get(type));
        }

        persistence.saveInventory(inventory, filePath);
    }
}