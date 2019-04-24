import java.sql.Connection;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class RqLeav extends JPanel {
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JList list;
	private String type[]={"None","Sick","Study","Unpaid","Paid"};
	public Session session;
	/**
	 * Create the panel.
	 */
	public RqLeav() {
		session=new Session();
		
		setLayout(null);
		
		
		JLabel lblStartDate = new JLabel("Start Date");
		lblStartDate.setBounds(78, 71, 58, 16);
		add(lblStartDate);
		
		textField_6 = new JTextField();
		textField_6.setBounds(173, 68, 116, 22);
		add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(173, 128, 116, 22);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblEndDate = new JLabel("End Date");
		lblEndDate.setBounds(78, 131, 56, 16);
		add(lblEndDate);
		
		
				
				list = new JList(type);
				//add(list);
				list.setBounds(168, 236, 93, 90);
				list.setSelectedIndex(0);
				JScrollPane scroll = new JScrollPane(list);
				scroll.setBounds(173, 237, 116, 22);	
				add(scroll);
				
				
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(78, 237, 56, 16);
		add(lblType);
		
		textField_8 = new JTextField();
		textField_8.setBounds(173, 183, 116, 22);
		add(textField_8);
		textField_8.setColumns(10);
		
		JLabel lblReason = new JLabel("Reason");
		lblReason.setBounds(78, 186, 56, 16);
		add(lblReason);
		
		
		setLayout(null);
		this.setBounds(220, 13, 600, 376);
		setLayout(null);
		
		
		
	}
	public void Request()
	{
		
		String sdate=textField_6.getText();
		String edate=textField_7.getText();
		String reason=textField_8.getText();
		String id=session.getId();
		String nam=session.getName();
		
		String type=this.type[list.getSelectedIndex()];
		Leaves obj=new Leaves();
		Connection connect=new Dbcon().connect();
		if(obj.add(nam, id, sdate, edate, reason, type, new Dbcon().connect())==1)
		{
			JOptionPane.showMessageDialog(new JFrame(),"Successful");
		}
		else
		{
			JOptionPane.showMessageDialog(new JFrame(),"UnSuccessful");
		}

	}
	
	
}
