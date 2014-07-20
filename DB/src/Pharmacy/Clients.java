package Pharmacy;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!
public class Clients  extends ConnectDB implements Tables  {

	private String firstname;
	private String lastname;
	private String EGN;
	private int ID ;
	ResultSet rs = null;
	Statement stmt = null;
	
	public Clients () 
	{
		super();
	}
	public void Insert(String name ,String lastname,String EGN)
	{
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery("call insert_client"	+ "('"+name+"'"+ ",'"+lastname+"'"+ ",'"+EGN+"')");
			
			//Нещо  не  сработва
	/*		rs = stmt.executeQuery(" USE  pharmacy; "+ "insert into clients (`name`,`lastname`,`EGN`)"
					+ " VALUES" +"("+ "'"+name+"',"
					+ "'"+ lastname +"',"
					+ "'"+EGN + "'"+");");*/

			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
/*			String db = " USE  pharmacy; "+ "insert into clients (`name`,`lastname`,`EGN`)"
					+ " VALUES" +"("+ "'"+name+"',"
					+ "'"+ lastname +"',"
					+ "'"+EGN + "'"+");";
			System.out.println(db);*/
		}
		
		//return getResults();
	}
	@Override
	public String Search(String key) {
		
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			rs = stmt.executeQuery("call search_client('"+key+"')");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return getResults();
	}
	
	@Override
	public String getTable() {

				try {
					stmt = conn.createStatement();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				try {
					rs = stmt.executeQuery("SELECT * FROM clients");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return getResults();
	}
	
	public String getEGN() {
		return EGN;
	}

	public void setEGN(String eGN) {
		EGN = eGN;
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
	@Override
	public String getResults() {
		ArrayList<String> result =  new ArrayList<String>();
		String view = "";
		//String  result = "\n";
		try {
			while (rs.next()) {
				setID(rs.getInt("c_id"));
				setFirstname(rs.getString("name"));
				setLastname(rs.getString("lastname"));
				setEGN(rs.getString("EGN"));
				result.add("<td align=center>"+ ID + " </td>"+"<td align=center>"+ firstname + " </td>"+ "<td align=center>"+ lastname + "</td>" + "<td align=center>"+ EGN + "</td>");

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String s : result)
		{
		    view += "<tr>"+ s + "</tr>" ;
		}
		// return HTML table 
		String title = " <td align=center bgcolor=#66FFCC>№</td>"
				+" <td align=center bgcolor=#66FFCC>Име</td>"
				+ " <td bgcolor=#66FFCC><div align=center>Фамилия</div></td> "
				+ "<td align=center bgcolor=#66FFCC> ЕГН</td>";
		
		return "<p> Клиенти </p>" + "<table width=320 border=4>"+ title + view + "<table>";
	}

}
