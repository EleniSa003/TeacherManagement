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

public class InsertFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfFirstname;
	private JTextField tfLastname;
	private JTextField tfAvailability;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InsertFrame frame = new InsertFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public InsertFrame() {
		setTitle("Νέα Εγγραφή Καθηγητή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Όνομα:");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel.setBounds(23, 56, 48, 16);
		contentPane.add(lblNewLabel);
		
		tfFirstname = new JTextField();
		tfFirstname.setBounds(70, 51, 130, 26);
		contentPane.add(tfFirstname);
		tfFirstname.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Επώνυμο:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(23, 84, 74, 16);
		contentPane.add(lblNewLabel_1);
		
		tfLastname = new JTextField();
		tfLastname.setBounds(93, 79, 106, 26);
		contentPane.add(tfLastname);
		tfLastname.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Ώρες/εβδομάδα:");
		lblNewLabel_2.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(23, 112, 117, 16);
		contentPane.add(lblNewLabel_2);
		
		tfAvailability = new JTextField();
		tfAvailability.setBounds(128, 107, 72, 26);
		contentPane.add(tfAvailability);
		tfAvailability.setColumns(10);
		
		JButton btnNewButton = new JButton("Εισαγωγή");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String firstname = tfFirstname.getText();
				String lastname = tfLastname.getText();
				String availability = tfAvailability.getText();
				if(firstname.isEmpty() || lastname.isEmpty() || availability.isEmpty()) {

				    JOptionPane.showMessageDialog(contentPane,
				            "Παρακαλώ συμπληρώστε όλα τα πεδία.");

				    return;
				}

				try {
				    Connection conn = DatabaseConnection.getConnection();

				    String sql = "INSERT INTO teachers (firstname, lastname, availability) VALUES (?, ?, ?)";
				    PreparedStatement pst = conn.prepareStatement(sql);

				    pst.setString(1, firstname);
				    pst.setString(2, lastname);
				    pst.setInt(3, Integer.parseInt(availability));

				    pst.executeUpdate();

				    JOptionPane.showMessageDialog(contentPane, "Ο καθηγητής καταχωρήθηκε στη βάση δεδομένων.");

				    tfFirstname.setText("");
				    tfLastname.setText("");
				    tfAvailability.setText("");

				} catch (Exception ex) {
				    JOptionPane.showMessageDialog(contentPane, "Σφάλμα καταχώρησης: " + ex.getMessage());
				}	
			}
		});
		btnNewButton.setBounds(114, 237, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Άκυρο");
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Program.insertFrame.setVisible(false); //krivei insert name kai epistrefei piso stin forna
				Program.startFrame.setEnabled(true);	
			}
		});
		btnNewButton_1.setBounds(286, 237, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_3 = new JLabel("Νέα Εγγραφή Καθηγητή");
		lblNewLabel_3.setForeground(Color.BLUE);
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel_3.setBounds(161, 17, 213, 22);
		contentPane.add(lblNewLabel_3);

	}
}
