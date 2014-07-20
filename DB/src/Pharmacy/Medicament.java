package Pharmacy;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

// Notice, do not import com.mysql.jdbc.*
// or you will have problems!
public class Medicament extends ConnectDB implements Tables  {

	private String Name;
	private String Dosage;
	private String Instruction;
	private int ID ;
	private double Price;
	Statement stmt = null;
	ResultSet rs = null;
	
	public Medicament() 
	{
		super();
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
			rs = stmt.executeQuery("call search_medicament('"+key+"')");
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
					rs = stmt.executeQuery("SELECT * FROM medicament");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				return getResults();
	}
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDosage() {
		return Dosage;
	}

	public void setDosage(String dosage) {
		Dosage = dosage;
	}

	public String getInstruction() {
		return Instruction;
	}

	public void setInstruction(String instruction) {
		Instruction = instruction;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public double getPrice() {
		return Price;
	}

	public void setPrice(double price) {
		Price = price;
	}

	
	@Override
	public String getResults() {
		ArrayList<String> result =  new ArrayList<String>();
		String view = "";
		//String  result = "\n";
		try {
			while (rs.next()) {
				setID(rs.getInt("m_id"));
				setName(rs.getString("name"));
				setDosage(rs.getString("dosage"));
				setInstruction(rs.getString("instructions"));
				setPrice(rs.getDouble("price"));
				result.add("<td align=center>"+ ID + " </td>"+"<td align=center>"+ Name + " </td>"+ "<td align=center>"+ Dosage + "</td>" + "<td align=center>"+ Instruction + "</td>"+ "</td>" + "<td align=center>"+ Price + "</td>");

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
				+ " <td bgcolor=#66FFCC><div align=center>Доза</div></td> "
				+ "<td align=center bgcolor=#66FFCC> Инструкция</td>"
				+ "<td align=center bgcolor=#66FFCC> Цена</td>";
		
		return "<p> Медикаменти </p>" + "<table width=320 border=4>"+ title + view + "<table>";
	}



}
