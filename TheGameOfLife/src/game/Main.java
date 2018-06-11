package game;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class Main {
	public static void main(String[] args) throws InterruptedException, IOException {
		JFrame frame = new JFrame("The Game of Life");
		frame.setLocation(700, 50);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		JLabel label = new JLabel("The Game of Life");
		JButton startButton = new JButton("Start");
//		GridLayout grid = new GridLayout();
//		grid.setColumns(10);
//		grid.setHgap(0);
//		grid.setRows(10);
//		grid.setVgap(0);
		
		JPanel jPanel = new JPanel();
		jPanel.setPreferredSize(new Dimension(700, 700));
		GameBoard gb = new GameBoard();
		gb.init();
		label.setText(gb.printBoard());
		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				gb.generate();
				try {
					label.setText(gb.printBoard());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		//jPanel.setLayout(grid);
		jPanel.add(label);
		jPanel.add(startButton);
		frame.getContentPane().add(jPanel);
		frame.pack(); // adjust width
		frame.setVisible(true);
	}
}
