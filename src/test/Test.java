package test;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

import util.CircleProgressBar;
import util.ColorUtil;
import util.GUIUtil;

public class Test {

	public static void main(String[] args) {
		
		GUIUtil.useLNF();
		JPanel p = new JPanel(); // 面板
		CircleProgressBar cpb = new CircleProgressBar(); // 进度条组件
		cpb.setBackgroundColor(ColorUtil.blueColor);
		cpb.setProgress(0);
		JButton b = new JButton("点击"); // 按钮
		p.setLayout(new BorderLayout());
		p.add(cpb, BorderLayout.CENTER);
		p.add(b, BorderLayout.SOUTH);
		GUIUtil.showPanel(p); // 显示面板
		
		// 给按钮加监听
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SwingWorker() {
					protected Object doInBackground() throws Exception {
						for (int i = 0; i < 100; i++) {
							cpb.setProgress(i + 1);
							cpb.setForegroundColor(ColorUtil.getByPercentage(i + 1));
							
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
						return null; 
					}
				}.execute();
			}
		});
	}
}
