import java.io.*;
import java.util.*;
import javax.swing.*;


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
		customer = new ArrayList();
	}
	
	public void addCustomer()
	{
		Scanner inp = new Scanner(System.in);
		
		System.out.println("Enter Name: " );
		String name = inp.nextLine();
		
		System.out.println("Enter id  " );
		String id = inp.nextLine();
		
		System.out.println("Enter age : " );
		int age =inp.nextInt();

		if(age < 18 || age> 65 ) 
		{
			throw new invalidAgeException();
		}
		
		System.out.println("Enter balanace amount: " );
		double balance= inp.nextDouble();
		if( balance <0.0 )
		{
			throw new lowBalanceException();
		}
		
		Customer cus1= new Customer(name, id , age, balance);
		customer.add(cus1);
		inp.close();
		
		
	}
	
	public void searchCustomer(String id) throws invalidCustomerIDException
	{
		for(int i=0 ; i<customer.size() ; i++)
		{
			Customer cus1=(Customer)customer.get(i);

			
			if(id.equals(cus1.id))
			{
				throw new invalidCustomerIDException();
			}
			else {
				cus1.toString();
				}
		}
	}
	
	
	

}



class lowBalanceException extends Exception
{
	double balance;
		public String toString() 
		{
			return "Customer has low balance" ;
		}
}


class duplicateCustomerException extends Exception
{
		public String toString() 
		{
			return "Customer with the same ID already exists!" ;
		}
}


	
class invalidAgeException extends Exception
{
	double age;
		public String toString()
		{
			
			return "Customer with the invalid age !" ;
		
		}
		
}

class invalidCustomerIDException  extends Exception
{
	int id;
	
		public String toString()
		{
			return "Customer ID doesn't exist..!!!";

		}
}
	
	

public class Main {
    public static void main(String[] args) {}
}
