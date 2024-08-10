import java.util.*;
import java.util.ArrayList;



class Customer 
{
	 String name;
	 String id;
	 int age;
	 double balance;
	
	Customer (String name , String id , int age, double balance)
	{
		this.name= name;
		this.id= id;
		this.age=age;
		this.balance= balance;
	}
	
	public String toString()
	{
		return name + " " + id + " " + age + " " +balance;
	}
}

class Bank
{
	ArrayList<Customer> customer;
	//Constructor

	public Bank()
	{
		customer = new ArrayList<>();
	}
	
	public void addCustomer() throws duplicateCustomerException, invalidAgeException,lowBalanceException
	{
			Scanner inp = new Scanner(System.in);
			System.out.println("Enter Name: " );
			String name = inp.nextLine();
			
			System.out.println("Enter id  " );
			String id = inp.nextLine();
			
			System.out.println("Enter age : " );
			int age =inp.nextInt();
			
			
			if(age < 18 || age > 65 ) 
			{
				throw new invalidAgeException();
			}
			
			System.out.println("Enter balanace amount: " );
			double balance= inp.nextDouble();
			if(balance <0)
			{
				throw new lowBalanceException(balance);
			}
			
			Customer cus1= new Customer(name,id,age,balance);
			customer.add(cus1);
	}
		
		

	
	public void searchCustomer(String id) throws invalidCustomerIDException
	{
		for(int i=0 ; i<customer.size() ; i++)
		{
			Customer cus1=(Customer)customer.get(i);
			if (cus1.id.equals(id)) {
				
                System.out.println("Name: " + customer[i].toString());
                
                return;
            }
			else
			{
				throw new invalidCustomerIDException();
			}
			
		}
	}
	
	
	

}



class lowBalanceException extends Exception
{
		public lowBalanceException(Double bal) {
        super("Customer has "+ bal + " low balance");
    }
	
}


class duplicateCustomerException extends Exception
{
		public duplicateCustomerException()
		{
			super("Already customer exist---!!");
		}
}


	
class invalidAgeException extends Exception
{
	public invalidAgeException()
	{
		super("invalid age");
	}
}

class invalidCustomerIDException  extends Exception
{
		public invalidCustomerIDException()
		{
			super("Invalid customer..!!");
		}
}
	



public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank();
        try {
            bank.addCustomer();
            // bank.addCustomer("Jane Doe", "67890", 70, 2000); // should throw InvalidAgeException
            // bank.addCustomer("Alice Smith", "12345", 30, -500); // should throw LowBalanceException
        } catch (duplicateCustomerException e) {
            System.out.println(e.getMessage());
        } catch (invalidAgeException e) {
            System.out.println(e.getMessage());
        } catch (lowBalanceException e) {
            System.out.println(e.getMessage());
        }
        
        try {
			Scanner inp= new Scanner(System.in);
			System.out.println("Enter Required Id:");
			String id= inp.nextLine();
            bank.searchCustomer(id);
    
        } catch (invalidCustomerIDException e) {
            System.out.println(e.getMessage());
        }
    }
}