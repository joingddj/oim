/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oimchat.app.awt.image.util;

import java.awt.GraphicsConfiguration;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 * 
 * @author XiaHui
 */
@SuppressWarnings("serial")
public class TextImageFrameTest extends JFrame {

	JPanel panel = new JPanel();
	JLabel label = new JLabel();

	public TextImageFrameTest() {
		super();
		initBaseFrame();
	}

	public TextImageFrameTest(GraphicsConfiguration gc) {
		super(gc);
		initBaseFrame();
	}

	public TextImageFrameTest(String title) {
		super(title);
		initBaseFrame();
	}

	public TextImageFrameTest(String title, GraphicsConfiguration gc) {
		super(title, gc);
		initBaseFrame();
	}

	private void initBaseFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 480);
		this.setMinimumSize(new java.awt.Dimension(100, 50));
		this.setLocationRelativeTo(null);
		this.setLayout(null);

	}

	public static void main(String[] args) {
		// TODO Auto-generated constructor stub
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				TextImageFrameTest mainFrame = new TextImageFrameTest();
				mainFrame.setVisible(true);
			}
		});
	}
}
