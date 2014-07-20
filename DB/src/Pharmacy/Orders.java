package Pharmacy;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!
public class Orders extends ConnectDB implements Tables  {

	private String firstname;
	private String lastname;
	private int ID ;
	private String Date;
	private String Product;
	Statement stmt = null;
	ResultSet rs = null;
	
	public Orders () 
	{
		super();
	}
	
	public String getTable() {

				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery("call show_drug_taken()");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				return getResults();
	}
	

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getProduct() {
		return Product;
	}

	public void setProduct(String product) {
		Product = product;
	}

	@Override
	public String Search(String key) {
		
		try {
			rs = stmt.executeQuery("call show_drug_taken()");
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String getResults() {
		ArrayList<String> result =  new ArrayList<String>();
		String view = "";
		//String  result = "\n";
		try {
			while (rs.next()) {
				setID(rs.getInt("drt_id"));
				setFirstname(rs.getString("Name"));
				setLastname(rs.getString("Lastname"));
				setProduct(rs.getString("Product"));
				setDate(rs.getDate("datetaken").toString());
				result.add("<td align=center>"+ ID 
						+ " </td>"
						+"<td align=center>"
						+ firstname + " </td>"
						+ "<td align=center>"
						+ lastname + "</td>" 
						+ "<td align=center>"
						+ Product + "</td>"
						+ "<td align=center>"
						+ Date + "</td>");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : result)
		{
		    view += "<tr>"+ s + "</tr>" ;
		}

		String title = " <td align=center bgcolor=#66FFCC>№</td>"
				+" <td align=center bgcolor=#66FFCC>Име</td>"
				+ " <td bgcolor=#66FFCC><div align=center>Фамилия</div></td> "
				+ " <td bgcolor=#66FFCC><div align=center>Продукт</div></td> "
				+ " <td bgcolor=#66FFCC><div align=center>Дата</div></td> ";
		
		return "<p> Поръчки </p>" + "<table width=320 border=4>"+ title + view + "<table>";
	}
}
