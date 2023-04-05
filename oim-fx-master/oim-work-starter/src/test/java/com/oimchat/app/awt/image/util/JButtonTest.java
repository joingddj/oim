package com.oimchat.app.awt.image.util;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.oimchat.app.awt.font.util.FontUtil;

/**
 * @author XiaHui
 * @date 2015年1月5日 上午11:52:00
 */
public class JButtonTest extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panel = new JPanel();
	private JButton radioButton = new JButton();
	private JButton radioButtonN = new JButton();
	private JButton radioButtonS = new JButton();

	/**
	 * Create the frame.
	 */
	public JButtonTest() {
		initComponent();
		initEvent();
	}

	private void initComponent() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon("images/40.png").getImage());
		this.setTitle("测试窗口");
		// this.setShowIconImage(false);
		// this.setShowTitle(false);
		getContentPane().setLayout(new CardLayout());

		// panel.setBounds(0, 0, 450, 300);
		panel.setBackground(new Color(190, 223, 203));
		panel.setLayout(null);

		radioButton.setText("确定");
		radioButtonN.setText("确定");
		radioButtonS.setText("确定");

		radioButton.setBounds(10, 50, 120, 25);
		radioButtonN.setBounds(10, 85, 120, 25);
		radioButtonS.setBounds(10, 120, 520, 25);

		radioButtonN.setEnabled(false);
		// radioButtonS.setEnabled(false);

		radioButtonS.setSelected(true);

		panel.add(radioButton);
		panel.add(radioButtonN);
		panel.add(radioButtonS);
		getContentPane().add(panel);

		BufferedImage image = AwtImageUtil.drawTextBufferedImage("@张三hhsah哈", FontUtil.getDefaultFont(), new Color(98, 84, 216), 0.1F);

		ImageIcon icon = new ImageIcon();
		icon.setImage(image);

		radioButtonS.setIcon(icon);

	}

	private void initEvent() {
		radioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				buttonAction();
			}
		});
	}

	private void buttonAction() {
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JButtonTest frame = new JButtonTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
