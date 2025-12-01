import java.util.*;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private ShippersDataManager dm;

    public UI(ShippersDataManager dm){
        this.dm = dm;
    }

    public void HomeScreen(){
        boolean in = true;
        while(in) {
            System.out.println("""
                    What do you want to do?
                    1) Add new shipper
                    2) Update shipper's phone number
                    3) Delete existing shipper
                    0) Exit
                    """);
            int choice = Integer.parseInt(scanner.nextLine());
            switch(choice) {
                case 1 -> {
                    System.out.println("Please enter the following shipper data you're adding:\n");
                    System.out.println("Shipper's name:\n");
                    String name = scanner.nextLine();
                    System.out.println("Shipper's phone number:\n");
                    String phone = scanner.nextLine();
                    dm.addNewShipper(name, phone);
                }
                case 2 -> {
                    System.out.println("Enter the ID of the shipper you want to make changes to:\n");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.println("Enter the new phone number:\n");
                    String newPhone = scanner.nextLine();
                    dm.changeShipperInfo(id, newPhone);
                }
                case 3 -> {
                    System.out.println("Enter the ID of the shipper you want to delete that is NOT 1-3:\n");
                    int id = Integer.parseInt(scanner.nextLine());
                    dm.deleteShipper(id);
                }
                case 0 -> {
                    System.out.println("Exiting program...Good bye!");
                    in = false;
                }
            }
        }
    }
}
