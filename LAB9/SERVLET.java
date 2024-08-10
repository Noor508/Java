import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PersonInfoServlet extends HttpServlet {
    // Implement doGet and doPost methods here
}


public class PersonInfoServlet extends HttpServlet {
    // ...

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Connect to the database and retrieve all records
        // Assume you have a PersonDAO class that handles database operations
        List<Person> persons = PersonDAO.getAllPersons();

        // Generate HTML response
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Address Book</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<table>");
        out.println("<tr><th>Name</th><th>Address</th><th>Phone</th></tr>");

        // Generate table rows dynamically
        for (Person person : persons) {
            out.println("<tr>");
            out.println("<td>" + person.getName() + "</td>");
            out.println("<td>" + person.getAddress() + "</td>");
            out.println("<td>" + person.getPhone() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("</body>");
        out.println("</html>");
    }
}


public class PersonInfoServlet extends HttpServlet {
    // ...

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the action parameter from the form
        String action = request.getParameter("action");

        if (action.equals("create")) {
            // Get the form data for creating a new person
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            // Create a new Person object and save it in the database
            Person newPerson = new Person(name, address, phone);
            PersonDAO.createPerson(newPerson);

        } else if (action.equals("update")) {
            // Get the form data for updating an existing person
            String name = request.getParameter("name");
            String address = request.getParameter("address");
            String phone = request.getParameter("phone");

            // Update the person's record in the database
            Person updatedPerson = new Person(name, address, phone);
            PersonDAO.updatePerson(updatedPerson);

        } else if (action.equals("delete")) {
            // Get the form data for deleting a person
            String name = request.getParameter("name");

            // Delete the person's record from the database
            PersonDAO.deletePerson(name);
        }

        // Redirect the user back to the doGet method to refresh the table
        response.sendRedirect(request.getContextPath() + "/personInfo");
    }
}


<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns="http://xmlns.jcp.org/xml/ns/javaee"
    xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
    http://xmlns.jcp.org/xml/ns/javaee/web-app_4_0.xsd"
    id="WebApp_ID" version="4.0">

    <servlet>
        <servlet-name>personInfoServlet</servlet-name>
        <servlet-class>com.example.PersonInfoServlet</servlet-class>
    </servlet>

    <servlet-mapping>
        <servlet-name>personInfoServlet</servlet-name>
        <url-pattern>/personInfo</url-pattern>
    </servlet-mapping>

    <!-- Other configurations and servlet mappings -->
</web-app>


<!DOCTYPE html>
<html>
<head>
    <title>Address Book</title>
</head>
<body>
    <h2>Add Person</h2>
    <form action="/personInfo" method="post">
        <input type="hidden" name="action" value="create">
        <label>Name:</label>
        <input type="text" name="name" required><br>
        <label>Address:</label>
        <input type="text" name="address" required><br>
        <label>Phone:</label>
        <input type="text" name="phone" required><br>
        <input type="submit" value="Add">
    </form>

    <h2>Update Person</h2>
    <form action="/personInfo" method="post">
        <input type="hidden" name="action" value="update">
        <label>Name:</label>
        <input type="text" name="name" required><br>
        <label>Address:</label>
        <input type="text" name="address" required><br>
        <label>Phone:</label>
        <input type="text" name="phone" required><br>
        <input type="submit" value="Update">
    </form>

    <h2>Delete Person</h2>
    <form action="/personInfo" method="post">
        <input type="hidden" name="action" value="delete">
        <label>Name:</label>
        <input type="text" name="name" required><br>
        <input type="submit" value="Delete">
    </form>
</body>
</html>