package main;

import java.awt.EventQueue;

public class Program {

	static StartFrame startFrame;
	static InsertFrame insertFrame;
	static ResultsFrame resultsFrame;
	static CoursesFrame coursesFrame;

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {

			public void run() {

				try {

					startFrame = new StartFrame();
					startFrame.setLocationByPlatform(true);
					startFrame.setVisible(true);

					insertFrame = new InsertFrame();
					insertFrame.setLocationByPlatform(true);
					insertFrame.setVisible(false);

					resultsFrame = new ResultsFrame();
					resultsFrame.setLocationByPlatform(true);
					resultsFrame.setVisible(false);

					coursesFrame = new CoursesFrame();
					coursesFrame.setLocationByPlatform(true);
					coursesFrame.setVisible(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
