package main;

import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Color;
import javax.swing.JOptionPane;

public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfSearchKey;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartFrame frame = new StartFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public StartFrame() {
		setTitle("Διαχείριση Καθηγητών");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Νέα Εγγραφή");
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				Program.insertFrame.setVisible(true);
			Program.startFrame.setEnabled(false);
			}
		});
		btnNewButton.setBounds(364, 237, 130, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Καλωσήρθατε στην εφαρμογή");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setBounds(130, 24, 257, 21);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Επώνυμο:");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(152, 60, 70, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton_1 = new JButton("Αναζήτηση");
		btnNewButton_1.setBackground(new Color(54, 51, 249));
		btnNewButton_1.setForeground(SystemColor.activeCaptionText);
		btnNewButton_1.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_1.addActionListener(new ActionListener() {
		
			public void actionPerformed(ActionEvent e) {

			    try {

			        Connection conn = DatabaseConnection.getConnection();

			        String sql = "SELECT * FROM teachers WHERE lastname = ?";

			        PreparedStatement pst = conn.prepareStatement(sql);

			        pst.setString(1, tfSearchKey.getText());

			        ResultSet rs = pst.executeQuery();

			        if(rs.next()) {

			            Program.resultsFrame.setTeacherData(
			                rs.getInt("id"),
			                rs.getString("firstname"),
			                rs.getString("lastname"),
			                rs.getInt("availability")
			            );

			            Program.resultsFrame.setVisible(true);
			            Program.startFrame.setEnabled(false);

			        } else {

			            JOptionPane.showMessageDialog(contentPane,
			                    "Δεν βρέθηκε καθηγητής.");

			        }

			    } catch (Exception ex) {

			        JOptionPane.showMessageDialog(contentPane,
			                "Σφάλμα αναζήτησης: " + ex.getMessage());

			    }
			}
			
			
		});
		btnNewButton_1.setBounds(229, 84, 117, 29);
		contentPane.add(btnNewButton_1);
		
		tfSearchKey = new JTextField();
		tfSearchKey.setBounds(219, 55, 130, 26);
		contentPane.add(tfSearchKey);
		tfSearchKey.setColumns(10);

	}

}
