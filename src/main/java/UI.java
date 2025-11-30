import java.util.*;

public class UI {
    private final Scanner scanner = new Scanner(System.in);
    private ShippersDataManager dm;

    public UI(ShippersDataManager dm){
        this.dm = dm;
    }

    public void HomeScreen(){
        System.out.println("Please enter the following shipper data:\n");
        System.out.println("Shipper's name:\n");
        String name = scanner.nextLine();
        System.out.println("Shipper's phone number:\n");
        String phone = scanner.nextLine();

        dm.addNewShipper(name, phone);
    }

}
