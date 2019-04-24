import java.awt.CardLayout;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Main_P {
	int flagForLeav = 0;
	private JPanel frame;
	private DefaultTableModel model;
	private JFrame frame1;
	private JTextField textField;
	private JPasswordField passwordField;
	private CardLayout c1;
	private JTable table;
	private Connection connect;
	private AddStaff obj;
	private ChangeP cpass;
	public Session session;
	private RqLeav rqleav;
	private Staff staff;
	private Leaves leav;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_P window = new Main_P();
					window.frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws SQLException
	 */
	public Main_P() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws SQLException
	 */
	private void initialize() throws SQLException {
		leav = new Leaves();
		session = new Session();

		obj = new AddStaff();
		connect = new Dbcon().connect();

		frame1 = new JFrame();
		frame1.setBounds(100, 100, 850, 600);
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame = new JPanel();
		c1 = new CardLayout();
		// frame.getContentPane().setLayout(c1);
		frame.setLayout(c1);
		frame1.getContentPane().add(frame);

		final JPanel Hr = new JPanel();
		JPanel login = new JPanel();
		staff = new Staff();

		frame.add(login, "Login");
		frame.add(Hr, "Hr");
		frame.add(staff, "Staff");

		Hr.setLayout(null);

		final JPanel Draw = new JPanel(c1);
		Draw.setBounds(220, 13, 600, 347);

		Hr.add(Draw);

		model = buildTableModel(obj.populateHR(connect));
		table = new JTable(model);
		table.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if(flagForLeav==1)
				{
					System.out.println(table.getSelectedRow());
				}
				
				
			}
		});

		final AddS addS = new AddS();
		addS.setBackground(SystemColor.activeCaption);
		addS.setBounds(220, 13, 600, 347);

		cpass = new ChangeP();

		Draw.add(table, "table");
		Draw.add(addS, "StaffAdd");
		Draw.add(cpass, "ChangePass");
		addS.setLayout(null);

		JButton btnAddNewEmp = new JButton("Add New Emp");
		btnAddNewEmp.setBounds(12, 95, 153, 25);
		btnAddNewEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flagForLeav = 0;
				c1.show(Draw, "StaffAdd");
			}
		});
		Hr.add(btnAddNewEmp);

		login.setLayout(null);
		textField = new JTextField();
		textField.setBounds(367, 112, 225, 42);
		login.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(367, 196, 225, 42);
		login.add(passwordField);

		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(236, 209, 107, 16);
		login.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("ID");
		lblNewLabel_1.setBounds(313, 112, 19, 42);
		login.add(lblNewLabel_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String id = textField.getText();
				String p = passwordField.getText();

				if (obj.log(id, p, connect)) {
					session.setId(id);
					ArrayList<String> al = obj.SetSessionInfo(id, connect);
					session.setName(al.get(0));
					System.out.println(session.getName());
					if (obj.type(id, connect) == 1) {
						c1.show(frame, "Staff");
					} else {
						c1.show(frame, "Hr");
					}
				} else {
					JOptionPane.showMessageDialog(frame1,
							"Enter credentials properly");
				}
			}
		});

		btnNewButton.setBounds(382, 328, 97, 25);
		login.add(btnNewButton);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String name = addS.Name.getText();
				String Id = addS.id.getText();
				String Pno = addS.phoneno.getText();
				String Dob = addS.dob.getText();
				String Sdate = addS.sdate.getText();
				String Desig = addS.desig.getText();
				String Pword = addS.pword.getText();
				String Type = addS.type.getText();

				if (name.equals("") || Id.equals("") || Pno.equals("")
						|| Dob.equals("") || Sdate.equals("")
						|| Desig.equals("") || Pword.equals("")
						|| Type.equals("")) {
					JOptionPane.showMessageDialog(new JFrame(),
							"Enter credentials properly");
				} else {
					if (obj.Check(Id, connect)) {
						JOptionPane
								.showMessageDialog(addS, "Id already exists");
					} else {
						obj.addS(name, Id, Pno, Dob, Desig, Sdate, Pword, Type,
								connect);
						try {
							model = rowUpdate(model, obj.populateHR(connect));
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Hr.revalidate();
						Hr.repaint();

						c1.show(Draw, "table");
					}
				}
			}
		});
		btnSubmit.setBounds(437, 249, 97, 25);
		addS.add(btnSubmit);

		JButton chPass = new JButton("Change Password");
		chPass.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				flagForLeav = 0;
				c1.show(Draw, "ChangePass");

			}
		});
		chPass.setBounds(12, 52, 153, 25);
		Hr.add(chPass);

		JButton btnNewButton1 = new JButton("Submit");
		btnNewButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddStaff obj = new AddStaff();
				Connection connect = new Dbcon().connect();
				String session_id = session.getId();
				String p1 = cpass.passwordField.getText();
				String p2 = cpass.passwordField_1.getText();
				if (p1.equals(p2)) {
					int i = obj.changePass(session_id, p1, connect);
					try {
						model = rowUpdate(model, obj.populateHR(connect));
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Hr.revalidate();
					Hr.repaint();
					c1.show(Draw, "table");

				} else {
					JOptionPane.showMessageDialog(new JFrame(),
							"Enter credentials properly");
				}

			}
		});
		btnNewButton1.setBounds(250, 267, 97, 25);
		cpass.add(btnNewButton1);

		JButton btnViewRequest = new JButton("View Request");
		btnViewRequest.setBounds(10, 140, 155, 25);
		btnViewRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					flagForLeav=1;
					model = rowUpdate(model, leav.RequestL(connect));
					Hr.revalidate();
					Hr.repaint();
					c1.show(Draw, "table");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});

		Hr.add(btnViewRequest);

		JButton btnProcessLeave = new JButton("Process Leave");
		btnProcessLeave.setBounds(10, 180, 155, 25);
		btnProcessLeave.setVisible(false);
		Hr.add(btnProcessLeave);

	}

	public static DefaultTableModel buildTableModel(ResultSet rs)
			throws SQLException {

		ResultSetMetaData metaData = rs.getMetaData();
		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		Vector<Object> v = new Vector<Object>();
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

	private DefaultTableModel rowUpdate(DefaultTableModel model, ResultSet rs)
			throws Exception {

		if (model.getRowCount() > 0) {
			for (int i = model.getRowCount() - 1; i > -1; i--) {
				model.removeRow(i);
			}
		}
		ResultSetMetaData metaData = rs.getMetaData();
		// names of columns
		Vector<String> columnNames = new Vector<String>();
		int columnCount = metaData.getColumnCount();
		Vector<Object> v = new Vector<Object>();
		for (int column = 1; column <= columnCount; column++) {
			columnNames.add(metaData.getColumnName(column));
			v.add(metaData.getColumnName(column));
		}

		// data of the table
		// Vector<Vector<Object>> data = new Vector<Vector<Object>>();
		model.addRow(v);
		// data.add(v);
		while (rs.next()) {
			Vector<Object> vector = new Vector<Object>();
			for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
				vector.add(rs.getObject(columnIndex));
			}
			// data.add(vector);
			model.addRow(vector);
		}

		return model;
	}
}
