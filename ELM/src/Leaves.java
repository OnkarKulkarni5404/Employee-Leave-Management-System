import java.sql.*;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Leaves {

	//add leaves into database --all employees
	public int add(String name, String id, String sdate, String edate,
			String reason, String type, Connection con) {
		int result = 0;
		SimpleDateFormat formt = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date s = formt.parse(sdate);
			Date e = formt.parse(edate);
			long diff = s.getTime() - e.getTime();
			float total = (diff / (1000 * 60 * 60 * 24));
			String sqlq = "insert into leaves(name,id,startdate,enddate,total,reason,type,comment,process) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(sqlq);
			ps.setString(1, name);
			ps.setString(2, id);
			ps.setString(3, sdate);
			ps.setString(4, edate);
			ps.setInt(5, Math.abs(Math.round(total)));
			ps.setString(6, reason);
			ps.setString(7, type);
			ps.setString(8, "Not Seen");
			ps.setString(9, "Pending");
			result = ps.executeUpdate();

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return result;
	}

	//update leaves data ----only HR
	public int updateLeaves(String id, String comment, String process,
			Connection con) {
		int result = 0;

		try {
			PreparedStatement ps = con
					.prepareStatement("update leaves set comment=?,process=? where id=?");
			ps.setString(1, comment);
			ps.setString(2, process);
			ps.setString(3, id);
			result = ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public ResultSet LeaveLog(String id,Connection con)
	{
		
		try{
			String sqlq="select * from leaves where id=\""+id+"\"";
			Statement s=con.createStatement();
			return s.executeQuery(sqlq);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}
	public ResultSet RequestL(Connection con)
	{
		System.out.println("Requested");
		try{
			String sqlq="select * from leaves where process=\"Pending\"";
			Statement s=con.createStatement();
			return s.executeQuery(sqlq);	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
