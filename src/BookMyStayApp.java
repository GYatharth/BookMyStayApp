import java.util.Map;

public class BookMyStayApp {

    public static void main(String[] args) {

        Room singleRoom = new SingleRoom();
        Room doubleRoom = new DoubleRoom();
        Room suiteRoom = new SuiteRoom();

        RoomInventory inventory = new RoomInventory();

        System.out.println("Hotel Room Inventory\n");

        System.out.println("Single Room:");
        singleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Single Room"));

        System.out.println("\nDouble Room:");
        doubleRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Double Room"));

        System.out.println("\nSuite Room:");
        suiteRoom.displayRoomDetails();
        System.out.println("Available: " + inventory.getRoomAvailability().get("Suite Room"));

        inventory.updateAvailability("Single Room", 4);

        System.out.println("\nUpdated Single Room Availability: " +
                inventory.getRoomAvailability().get("Single Room"));
    }
}