import java.util.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Application needs two username and password to run.");
            System.exit(1); //exits program when required arguments are not provided with exit code 1
        }

        ShippersDataManager dm = new ShippersDataManager(args[0], args[1]);
        UI ui = new UI(dm);
        ui.HomeScreen();
    }
}
/*
You will create a program that leads the user on the following journey:
1. Prompt the user for new shipper data (name and phone) and then insert it
into the shippers table. Display the new shipper id when the insert is
complete.
2. Run a query and display all of the shippers
3. Prompt the user to change the phone number of a shipper. They should
enter the id and the phone number.
4. Run a query and display all of the shippers
5. Prompt the user to delete a shipper. DO NOT ENTER SHIPPERS 1-3.
They have related data in other tables. Delete your new shipper.
6. Run a query and display all of the shipper
 */