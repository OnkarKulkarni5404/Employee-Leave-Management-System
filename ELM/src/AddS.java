import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;


public class AddS extends JPanel {
	public JTextField Name;
	public JTextField id;
	public JTextField phoneno;
	public JTextField dob;
	public JTextField sdate;
	public JTextField desig;
	public JTextField pword;
	public JTextField type;
	public AddStaff obj;
	public Connection connect;

	
	public AddS() {
		connect = new Dbcon().connect();
		
		
		this.setBackground(SystemColor.activeCaption);
		this.setBounds(220,13,600,347);
		setLayout(null);
		
		JLabel lblId = new JLabel("Id:");
		lblId.setBounds(57, 93, 56, 16);
		add(lblId);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(57, 135, 56, 16);
		add(lblPhone);
		
		JLabel lblDob = new JLabel("DOB:");
		lblDob.setBounds(57, 176, 56, 16);
		add(lblDob);
		
		JLabel lblName = new JLabel("Name:");
		lblName.setBounds(57, 58, 56, 16);
		add(lblName);
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(57, 215, 69, 16);
		add(lblStartDate);
		
		JLabel lblEndDate = new JLabel("Designation");
		lblEndDate.setBounds(42, 253, 69, 16);
		add(lblEndDate);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(370, 58, 56, 16);
		add(lblNewLabel);
		
		JLabel lblType = new JLabel("Type:");
		lblType.setBounds(370, 93, 56, 16);
		add(lblType);
		
		Name = new JTextField();
		Name.setBounds(125, 55, 116, 22);
		add(Name);
		Name.setColumns(10);
		
		id = new JTextField();
		id.setBounds(125, 90, 116, 22);
		add(id);
		id.setColumns(10);
		
		phoneno = new JTextField();
		phoneno.setBounds(125, 132, 116, 22);
		add(phoneno);
		phoneno.setColumns(10);
		
		dob = new JTextField();
		dob.setBounds(125, 173, 116, 22);
		add(dob);
		dob.setColumns(10);
		
		sdate = new JTextField();
		sdate.setBounds(125, 212, 116, 22);
		add(sdate);
		sdate.setColumns(10);
		
		desig = new JTextField();
		desig.setBounds(125, 250, 116, 22);
		add(desig);
		desig.setColumns(10);
		
		pword = new JTextField();
		pword.setBounds(457, 55, 116, 22);
		add(pword);
		pword.setColumns(10);
		
		type = new JTextField();
		type.setBounds(457, 90, 116, 22);
		add(type);
		type.setColumns(10);
		
		obj=new AddStaff();
		
		
	}

}
