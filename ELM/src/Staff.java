import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;


public class Staff extends JPanel {

	private RqLeav rq=new RqLeav();
	private CardLayout c=new CardLayout();
	private JPanel SDraw ;
	private ChangeP cpass;
	private DefaultTableModel model;
	Session session=new Session();
	private JTable table;
	private Leaves leav;
	/**
	 * Create the panel.
	 */
	public Staff() {
		
		leav=new Leaves();
		this.setBounds(100, 100, 850, 600);	
		this.setLayout(null);
		cpass=new ChangeP();
		
		
		SDraw = new JPanel();
		SDraw.setBounds(220, 13, 600, 376);
		SDraw.setLayout(c);
		add(SDraw);
		
		try {
			model=this.buildTableModel(leav.LeaveLog(session.getId(), new Dbcon().connect()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		table=new JTable(model);
			
		
		SDraw.add(rq,"Request");
		SDraw.add(cpass,"ChangePassword");
		SDraw.add(table,"Table");
		
		JButton btnRequestLeaves = new JButton("Request Leaves");
		btnRequestLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			c.show(SDraw,"Request");
			}
		});
		btnRequestLeaves.setBounds(54, 142, 135, 25);
		add(btnRequestLeaves);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			c.show(SDraw,"ChangePassword");
			}
		});
		btnChangePassword.setBounds(54, 205, 135, 25);
		add(btnChangePassword);
		
		JButton btnLeavesLog = new JButton("Leaves Log");
		btnLeavesLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					model=rowUpdate(model,leav.LeaveLog(session.id, new Dbcon().connect()));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
				c.show(SDraw,"Table");
			}
		});
		btnLeavesLog.setBounds(54, 267, 135, 25);
		add(btnLeavesLog);
		
		JButton btnNewButton1 = new JButton("Submit");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			AddStaff obj=new AddStaff();
			Connection connect=new Dbcon().connect();
			String session_id=session.getId();
			String p1=cpass.passwordField.getText();
			String p2=cpass.passwordField_1.getText();
			if(p1.equals(p2))
			{
				int i=obj.changePass(session_id, p1, connect);				
				c.show(SDraw, "Request");
				
			}
			else
			{
				JOptionPane.showMessageDialog(new JFrame(),"Enter credentials properly");
			}
			
			
			}
		});
		btnNewButton1.setBounds(250, 267, 97, 25);
		cpass.add(btnNewButton1);
		
		JButton reqLeaves = new JButton("Submit");
		reqLeaves.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rq.Request();
			}
		});
		reqLeaves.setBounds(341, 233, 97, 25);
		rq.add(reqLeaves);
	
	}
	private static DefaultTableModel buildTableModel(ResultSet rs)
	        throws SQLException {

	    ResultSetMetaData metaData = rs.getMetaData();
	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    Vector<Object> v=new Vector<Object>();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	        v.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    data.add(v);
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	    }
	    
	    

	    return new DefaultTableModel(data, columnNames);

	}
	 DefaultTableModel rowUpdate(DefaultTableModel model,ResultSet rs) throws Exception
	{
		if (model.getRowCount() > 0) {
		    for (int i = model.getRowCount() - 1; i > -1; i--) {
		        model.removeRow(i);
		    }
		}
	    ResultSetMetaData metaData = rs.getMetaData();
	    // names of columns
	    Vector<String> columnNames = new Vector<String>();
	    int columnCount = metaData.getColumnCount();
	    Vector<Object> v=new Vector<Object>();
	    for (int column = 1; column <= columnCount; column++) {
	        columnNames.add(metaData.getColumnName(column));
	        v.add(metaData.getColumnName(column));
	    }

	    // data of the table
	    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
	    data.add(v);
	    while (rs.next()) {
	        Vector<Object> vector = new Vector<Object>();
	        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
	            vector.add(rs.getObject(columnIndex));
	        }
	        data.add(vector);
	        model.addRow(vector);
	    }
	 
	
	return model;
	}


}
