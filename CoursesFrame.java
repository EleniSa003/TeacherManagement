package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import java.awt.Font;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class CoursesFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private int teacherId;
	private JList<String> list;
	private JList<String> list_1;
	private JLabel lblTeacherName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CoursesFrame frame = new CoursesFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public CoursesFrame() {
		setTitle("Μαθήματα Καθηγητή");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Καθηγητής:");
		lblTeacherName = new JLabel("");
		lblTeacherName.setFont(new Font("Arial", Font.PLAIN, 16));
		lblTeacherName.setBounds(18, 27, 250, 16);
		contentPane.add(lblTeacherName);
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 17));
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setBounds(18, 0, 104, 29);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Διαθέσιμα Μαθήματα");
		lblNewLabel_1.setFont(new Font("Arial", Font.ITALIC, 14));
		lblNewLabel_1.setBounds(17, 50, 155, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Μαθήματα Καθηγητή");
		lblNewLabel_2.setFont(new Font("Arial", Font.ITALIC, 14));
		lblNewLabel_2.setBounds(301, 50, 143, 16);
		contentPane.add(lblNewLabel_2);
		
		list = new JList<String>();
		JScrollPane scrollPaneTeacher = new JScrollPane(list);
		scrollPaneTeacher.setBounds(301, 63, 143, 186);
		contentPane.add(scrollPaneTeacher);

		list_1 = new JList<String>();
		JScrollPane scrollPaneAvailable = new JScrollPane(list_1);
		scrollPaneAvailable.setBounds(18, 63, 143, 186);
		contentPane.add(scrollPaneAvailable);

		JButton btnNewButton = new JButton(">>");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        String selectedCourse = list_1.getSelectedValue();

		        if (selectedCourse != null) {
		            try {
		                Connection conn = DatabaseConnection.getConnection();
		                String checkSql = "SELECT teacher_id FROM courses WHERE title = ?";
		                PreparedStatement checkPst = conn.prepareStatement(checkSql);
		                checkPst.setString(1, selectedCourse);

		                ResultSet rs = checkPst.executeQuery();

		                if (rs.next()) {
		                    int existingTeacher = rs.getInt("teacher_id");

		                    if (existingTeacher != 0 && existingTeacher != teacherId) {
		                        int response = JOptionPane.showConfirmDialog(
		                                contentPane,
		                                "Το μάθημα διδάσκεται ήδη από άλλον καθηγητή. Θέλετε αντικατάσταση;",
		                                "Επιβεβαίωση",
		                                JOptionPane.YES_NO_OPTION
		                        );

		                        if (response != JOptionPane.YES_OPTION) {
		                            return;
		                        }
		                    }
		                }

		                String sql = "UPDATE courses SET teacher_id = ? WHERE title = ?";
		                PreparedStatement pst = conn.prepareStatement(sql);

		                pst.setInt(1, teacherId);
		                pst.setString(2, selectedCourse);

		                pst.executeUpdate();
		                loadCourses();

		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(contentPane,
		                        "Σφάλμα ανάθεσης μαθήματος: " + ex.getMessage());
		            }
		        }
		    }
		});
		btnNewButton.setBounds(172, 116, 117, 29);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("<<");
		btnNewButton_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        String selectedCourse = list.getSelectedValue();

		        if (selectedCourse != null) {
		            try {
		                Connection conn = DatabaseConnection.getConnection();
		                String sql = "UPDATE courses SET teacher_id = NULL WHERE title = ?";
		                PreparedStatement pst = conn.prepareStatement(sql);

		                pst.setString(1, selectedCourse);

		                pst.executeUpdate();
		                loadCourses();

		            } catch (Exception ex) {
		                JOptionPane.showMessageDialog(contentPane,
		                        "Σφάλμα αφαίρεσης μαθήματος: " + ex.getMessage());
		            }
		        }
		    }
		});
		btnNewButton_1.setBounds(172, 157, 117, 29);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Κλείσιμο");
		btnNewButton_2.setFont(new Font("Arial", Font.BOLD, 14));
		btnNewButton_2.addActionListener(new ActionListener() {
		   
			public void actionPerformed(ActionEvent e) {
		        Program.coursesFrame.setVisible(false);
		        Program.resultsFrame.setEnabled(true);
		    }
		});
		btnNewButton_2.setBounds(172, 220, 117, 29);
		contentPane.add(btnNewButton_2);
	
		
		contentPane.add(scrollPaneTeacher);

	} 
	public void setTeacherId(int id) {
	    this.teacherId = id;
	}
	public void setTeacherName(String name) {
	    lblTeacherName.setText(name);
	}
	
	public void loadCourses() {
	    try {
	        Connection conn = DatabaseConnection.getConnection();

	        DefaultListModel<String> availableModel = new DefaultListModel<>();
	        DefaultListModel<String> teacherModel = new DefaultListModel<>();

	        String availableSql = "SELECT title FROM courses";
	      //  availablePst.setInt(1, teacherId);
	        PreparedStatement availablePst = conn.prepareStatement(availableSql);
	       // availablePst.setInt(1, teacherId);
	        ResultSet availableRs = availablePst.executeQuery();

	        while (availableRs.next()) {
	            availableModel.addElement(availableRs.getString("title"));
	        }

	        String teacherSql = "SELECT title FROM courses WHERE teacher_id = ?";
	        PreparedStatement teacherPst = conn.prepareStatement(teacherSql);
	        teacherPst.setInt(1, teacherId);
	        ResultSet teacherRs = teacherPst.executeQuery();

	        while (teacherRs.next()) {
	            teacherModel.addElement(teacherRs.getString("title"));
	        }

	        list_1.setModel(availableModel);
	        list.setModel(teacherModel);

	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(contentPane, "Σφάλμα φόρτωσης μαθημάτων: " + ex.getMessage());
	    }
	}
}
