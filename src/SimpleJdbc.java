import java.sql.*;
import java.util.Scanner;

public class SimpleJdbc {
    public static void main(String[] args) throws SQLException {

        System.out.println("\n****** \nProgram til hentning af landedata fra world.sql databasen. \n****** ");

        // #1. Connect to the database
        Connection connection = null;
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost/world",
                "root",
                "CodeWarrior8"
        );

        System.out.println("\nDatabase connected.");

        // #2. Create a statement

        String landeNavn = "";

        // ** Med Scanner kan vi få brugeren til at indtaste, hvilket land, de vil se info om
        Scanner scanner = new Scanner(System.in);

        while (!landeNavn.equalsIgnoreCase("exit")) {
            System.out.print("Hvilket land vil du se oplysninger om? ±");
            landeNavn = scanner.nextLine();

            String mitQuery = "SELECT * FROM world.country WHERE name LIKE  '" + landeNavn + "';";


            // ** Uden scanner kan vi kun vise det land, som vi harcoder i query-et
            Statement statement = connection.createStatement();

            // #3. Execute the statement and send the SQL-query to the SQL-server
            ResultSet resultSet = statement.executeQuery
                    (mitQuery);
            System.out.println("Følgende query er sendt til MySQL-serveren: " + mitQuery);
            System.out.println("Svar fra databasen: " + "\n");


            // #4. Iterate through the result and print the results (vi har måske flere resultater end 1, derfor vil en løkke læse alle rækker ud fra resultSettet)
            while (resultSet.next())
                System.out.println(resultSet.getString(1) + " " +
                        resultSet.getString(2) + ", " +
                        resultSet.getString(3) + ", " +
                        "Population: " +
                        resultSet.getString("Population"));
        }

        // #5. Close the connection (always!)
        connection.close();
        System.out.println();
    }
}

