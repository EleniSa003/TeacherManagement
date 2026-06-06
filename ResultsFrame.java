package main;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;

public class ResultsFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfId;
	private JTextField tfFirstname;
	private JTextField tfLastname;
	private JTextField tfAvailability;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ResultsFrame frame = new ResultsFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public ResultsFrame() {
		setTitle("Αποτελέσματα Αναζήτησης");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Αποτελέσματα Αναζήτησης");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(19, 19, 230, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("ID:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(19, 56, 27, 16);
		contentPane.add(lblNewLabel_1);
		
		tfId = new JTextField();
		tfId.setEditable(false);
		tfId.setBounds(40, 51, 98, 26);
		contentPane.add(tfId);
		tfId.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Όνομα:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(19, 84, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		tfFirstname = new JTextField();
		tfFirstname.setBounds(68, 79, 112, 26);
		contentPane.add(tfFirstname);
		tfFirstname.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Επώνυμο:");
		lblNewLabel_3.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(19, 112, 76, 16);
		contentPane.add(lblNewLabel_3);
		
		tfLastname = new JTextField();
		tfLastname.setBounds(88, 107, 118, 26);
		contentPane.add(tfLastname);
		tfLastname.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("Διαθεσιμότητα:");
		lblNewLabel_4.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(19, 140, 112, 16);
		contentPane.add(lblNewLabel_4);
		
		tfAvailability = new JTextField();
		tfAvailability.setBounds(115, 135, 130, 26);
		contentPane.add(tfAvailability);
		tfAvailability.setColumns(10);
		
		JButton btnNewButton = new JButton("Ενημέρωση");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
	 Connection conn = DatabaseConnection.getConnection();

	 String sql = "UPDATE teachers SET firstname = ?, lastname = ?, availability = ? WHERE id = ?";
	 PreparedStatement pst = conn.prepareStatement(sql);

		pst.setString(1, tfFirstname.getText());
		 pst.setString(2, tfLastname.getText());
	    pst.setInt(3, Integer.parseInt(tfAvailability.getText()));
		 pst.setInt(4, Integer.parseInt(tfId.getText()));

		  pst.executeUpdate();

				    JOptionPane.showMessageDialog(contentPane, "Τα στοιχεία του καθηγητή ενημερώθηκαν στη βάση δεδομένων.");

				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(contentPane, "Σφάλμα ενημέρωσης: " + ex.getMessage());
				}
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.setBounds(6, 207, 118, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Διαγραφή");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int answer = JOptionPane.showConfirmDialog(
						contentPane,
						"Είστε σίγουροι ότι θέλετε να διαγράψετε τον καθηγητή;",
						"Επιβεβαίωση Διαγραφής",
						JOptionPane.YES_NO_OPTION
				);

				if(answer == JOptionPane.YES_OPTION) {
					try {

					    Connection conn = DatabaseConnection.getConnection();

					    String sql = "DELETE FROM teachers WHERE id = ?";

					    PreparedStatement pst = conn.prepareStatement(sql);

					    pst.setInt(1, Integer.parseInt(tfId.getText()));

					    pst.executeUpdate();

					    JOptionPane.showMessageDialog(contentPane,
					            "Ο καθηγητής διαγράφηκε επιτυχώς.");

					    tfId.setText("");
					    tfFirstname.setText("");
					    tfLastname.setText("");
					    tfAvailability.setText("");

					} catch (Exception ex) {

					    JOptionPane.showMessageDialog(contentPane,
					            "Σφάλμα διαγραφής: " + ex.getMessage());

					}
				}
			}
		});
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.setBounds(326, 237, 118, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Μαθήματα");
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Program.coursesFrame.setTeacherId(
					    Integer.parseInt(tfId.getText())
					);
				Program.coursesFrame.setTeacherName(
					    tfFirstname.getText() + " " + tfLastname.getText()
					);
				Program.coursesFrame.loadCourses();
				Program.coursesFrame.setVisible(true);
				Program.resultsFrame.setEnabled(false);
			}
		});
		btnNewButton_2.setBounds(6, 237, 118, 29);
		contentPane.add(btnNewButton_2);

	}
	public void setTeacherData(int id, String firstname, String lastname, int availability) {
	    tfId.setText(String.valueOf(id));
	    tfFirstname.setText(firstname);
	    tfLastname.setText(lastname);
	    tfAvailability.setText(String.valueOf(availability));
	}
}
