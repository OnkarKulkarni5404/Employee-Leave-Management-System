import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AddStaff {

	public int addS(String name, String id, String phone, String dob,
			String designation, String sdate, String pword, String type,
			Connection con) {
		String sqlq = "insert into emp values(?,?,?,?,?,?,?,?)";
		int result = 0;

		try {
			PreparedStatement ps = con.prepareStatement(sqlq);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, phone);
			ps.setString(4, dob);
			ps.setString(5, designation);
			ps.setString(6, sdate);
			ps.setString(7, pword);
			ps.setString(8, type);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//check if employee exists

	public boolean Check(String id, Connection con) {
		boolean result = false;
		String sqlq = "select * from emp where id=\"" + id + "\"";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sqlq);
			result = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	//login user

	public boolean log(String id, String pword, Connection con) {
		boolean result = false;
		String sqlq = "select * from emp where id=\"" + id
				+ "\" AND password=\"" + pword + "\"";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sqlq);
			result = rs.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	//check type
	public int type(String id,Connection con)
	{
		String sqlq = "select type from emp where id=\"" + id+"\"";
		int result=0;
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sqlq);
			rs.next();
			if(rs.getString("type").equals("Staff"))
			{
				return 1;
			}
			else
			{
				return 2;
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	
	
	//return Hr table of emp
	public ResultSet populateHR(Connection con) 
	{
	
		String sqlq="select * from emp";
		Statement ps;
		try {
			ps = con.createStatement();
			return ps.executeQuery(sqlq);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	
	}
	
	
	
	//change password
	
	public int changePass(String id,String pword,Connection con)
	{
		int result=0;
		String sqlq="update emp set password=? where id=?";
		try{
			PreparedStatement ps=con.prepareStatement(sqlq);
			ps.setString(1,pword);
			ps.setString(2, id);
			result=ps.executeUpdate();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList SetSessionInfo(String id,Connection con)
	{
		ArrayList<String> al=new ArrayList<String>();
		
		String sqlq="select name from emp where id=\""+id+"\"";
		try {
			Statement ps = con.createStatement();
			ResultSet rs = ps.executeQuery(sqlq);
			rs.next();
			al.add(rs.getString("name"));

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return al;	
	}
	

}
