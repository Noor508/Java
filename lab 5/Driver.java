import java.util.*;
import java.sql.*;


class AddressBook
{
	public Statement st;
	public Connection con;
	public String url;
	
	
		public AddressBook()
		{
			try
			{
			Class.forName("com.mysql.jdbc.Driver");
			 url = "jdbc:mysql://127.0.0.1/addressbook";
			 con=DriverManager.getConnection(url,"root","root");
			 st=con.createStatement();

			}
			catch(Exception e)
			{
				System.out.println("Coone:");
				e.getMessage();
	
			}
			
			System.out.println("Connection builded");
		}
		
void addPerson()
{
		try
		{
			Scanner inp = new Scanner (System.in);
			String u_name, address, city, phone;
			System.out.println("Enter the username:");
			u_name= inp.nextLine();
			
			
			System.out.println("Enter the address:");
			address= inp.nextLine();
			
			System.out.println("Enter the city:");
			city= inp.nextLine();
			
			System.out.println("Enter the phone:");
			phone= inp.nextLine();
			
			
			 
			 
			String query="insert into info(username,address,city,phone) values('"+u_name+"', '"+address+"', '"+city+"' ,"+phone+"  )";
			
			int rs = st.executeUpdate( query );

			 System.out.println(rs);
		   
			 if(rs > 0)
			 {
				System.out.println("Record inserted successfully.");
			  }
			 
			 else
			 {
				 System.out.println("Record could not inserted.");
				 }
		}
			catch(Exception b)
			{
				b.getMessage();
			}
		
}
		
void searchPerson(String u_name)
{
			
		try
		{
			String query="Select * from info where username='"+u_name+"' ";
			 
		  
			 ResultSet rzlt_set = st.executeQuery( query );
		   
			 if(rzlt_set.next())
			 {
					String name = rzlt_set.getString("username");
					String address = rzlt_set.getString("address");
					String city = rzlt_set.getString("city");
					String phone = rzlt_set.getString("phone");

					

					System.out.println("Name: "+ name +"\tAddress: "+ address + "City:" + city + "phone:"+phone);

			 }
			 
			 else
			 {
				 System.out.println("No record found");
			}
		
		}
		catch (Exception w)
		{
			w.getMessage();
		}
}
		
		
void deletePerson(String u_name)
{
	try
	{
				String query="DELETE  FROM info WHERE username=' "+u_name+" ' ";
				
				
				int result_Set = st.executeUpdate(query);
			   
				 if(result_Set>0)
				 {
						 System.out.println("Deleted");
				}
				 
				 else
				 {
					 System.out.println("No record found");
				 }
	}
	catch(Exception y)
		{
			y.getMessage();
		}
}
		
		
}




public class Driver
{
	
	public static void main(String args[]) throws Exception
	{
		AddressBook ab= new AddressBook();
		//ab.addPerson();
		//ab.deletePerson();
		
		
		
		
		Scanner scanner = new Scanner(System.in);

        while (true)
		{
            System.out.println("Address Book Menu:");
            System.out.println("1. Add a person");
            System.out.println("2. Delete a person");
            System.out.println("3. Search for a person");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) 
			{
                case 1:
                    ab.addPerson();
                    break;
                case 2:
                    System.out.print("Enter name to delete: ");
					String name =scanner.nextLine();
                    ab.deletePerson(name);
                    break;
                case 3:
                    System.out.print("Enter name to search: ");
                    String searchName = scanner.nextLine();
                    ab.searchPerson(searchName);
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("try again----!!");
            }
            System.out.println();
        }
  
		
	}
}