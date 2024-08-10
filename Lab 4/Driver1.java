import java.util.*;
import java.io.*;

class Employee {
    private int id;
    private String name;
    private static double salary;
    private String rank;

    public Employee(int id, String name, String rank) {
        this.id = id;
        this.name = name;
        this.rank = rank;
    }


    public int getId() {
        return id;
    }

    public void setId(int id1) {
        id = id1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name1) {
        name = name1;
    }

    public static double getSalary() {
        return salary;
    }

    public static void setSalary(double salary1) {
        Employee.salary = salary1;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank1) {
        rank = rank1;
    }
}
class EmployeeManager {
    public ArrayList <Employee> obj;

    public EmployeeManager() {
        this.obj = new ArrayList<Employee>();
    }
    public void load_addData() {

	//using buffer stream
       try (BufferedReader reader = new BufferedReader(new FileReader("EmployeeData.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                String rank = parts[2];
                Employee employee = new Employee(id, name, rank);
                obj.add(employee);
            }
	
            System.out.println("Data loaded successfully.");
        } catch (IOException e) {
            System.out.println("Error loading data from CSV: " + e.getMessage());
        }
    }
	

	public void addEmployee(Employee employee) {
        	obj.add(employee);
        	System.out.println("Employee added successfully.");
    	}


	public boolean remove(int id) {
        for (Employee employee : obj) {
            if (employee.getId() == id) {
                obj.remove(employee);
                System.out.println("removed successfully.");
                return true;
            }
        }
        System.out.println("Employee not found.");
        return false;
    	}


	public void writedata() {
//using buffer stream
	try (BufferedWriter writer = new BufferedWriter(new FileWriter("data.txt"))) {
            for (Employee employee : obj) {
                String line = employee.getId() + "," + employee.getName() + "," + employee.getRank() + "\n";
                writer.write(line);
            }
            System.out.println("Data saved to TXT file.");
        } catch (IOException e) {
            System.out.println("Error writing data to TXT: " + e.getMessage());
        }
    }

}


public class Driver1 {

    public static void main(String args[]) {
	EmployeeManager employeeManager= new EmployeeManager();
	Employee zainab=new Employee(123,"zainab","BPS-26");
	Employee.setSalary(34.5);
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Employee Management System");
            System.out.println("--------------------------");
            System.out.println("1. Load data from excel file");
            System.out.println("2. Add a new employee");
            System.out.println("3.Save data to new file.txt ");
            System.out.println("4. Remove an employee");
            System.out.println("anyother number to Exit");
            System.out.print("Enter your choice (1-5): ");
            String choice = scanner.nextLine();

            if (choice.equals("1")) {
                employeeManager.load_addData();
	    }else if(choice.equals("2")){
		employeeManager.addEmployee(zainab);
	    }else if(choice.equals("3")){
		employeeManager.writedata();
	    }else if(choice.equals("4")){
		employeeManager.remove(123);
	    }else{
		System.out.println("--------------------------");
		System.exit(0);
	    }
	}
	}
}
			
