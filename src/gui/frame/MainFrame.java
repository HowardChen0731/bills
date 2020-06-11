package gui.frame;

import gui.panel.MainPanel;

import javax.swing.*;

public class MainFrame extends JFrame {

	public static MainFrame instance = new MainFrame();

	private MainFrame() {
		this.setSize(500, 450);
		this.setTitle("����");
		this.setContentPane(MainPanel.instance);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		instance.setVisible(true);
	}
}