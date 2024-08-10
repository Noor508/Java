import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BookServlet extends HttpServlet {
    private ArrayList<Book> bookList;

    @Override
    public void init() throws ServletException {
        bookList = new ArrayList<>();
        bookList.add(new Book("It ends with us", "Colleni", "9053064"));
        bookList.add(new Book("Silent Patient", "John", "91976708"));
        bookList.add(new Book("jannat ky patay", "Numra ahmad", "4007737"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("<html><head><title>Book Collection</title></head><body>");
        out.println("<h1>Book Collection</h1>");
        

        for (Book book : bookList) {
            out.println("<tr>");
            out.println("<td>" + book.getTitle() + "</td>");
            out.println("<td>" + book.getAuthor() + "</td>");
            out.println("<td>" + book.getISBN() + "</td>");
            out.println("</tr>");
        }

        out.println("</table>");
        out.println("<h1>Add Book</h1>");
        out.println("<form action='/Book' method='GET'>");
        out.println("<tr> <td> Title <input type="text" name ="title" id ="title"><td>");
       out.println("<td> Author <input type="text" name ="author" id ="author"></td>");
	   out.println("<td> ISBN <input type="text" name ="isbn" id ="isbn" </td>")
	   out.println(" <td> <input type="submit" value="Add Book"  ></td>")

      

        out.println("</table>");
        out.println("<h1>Update Book</h1>");
        out.println("<form action='/Book' method='GET'>");
        out.println("<tr> <td> Title <input type="text" name ="title" id ="title"><td>");
       out.println("<td> Author <input type="text" name ="author" id ="author"></td>");
	   out.println("<td> ISBN <input type="text" name ="isbn" id ="isbn" </td>")
	   out.println(" <td> <input type="submit" value="Update Book"  ></td>")

        out.println("</table>");
        out.println("<h1>Delete Book</h1>");
        out.println("<form action='/Book' method='GET'>");
	   out.println("<td> ISBN <input type="text" name ="isbn" id ="isbn" </td>")
	   out.println(" <td> <input type="submit" value="delete Book"  ></td>")

        out.println("</body></html>");
    }

   
        doGet(request, response);
    }
}