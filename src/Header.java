import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.File;

public class Header extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel left;

	private JLabel headerLabel1;
	private JLabel headerLabel2;
	private JSlider slider;

	private JPanel right;
	private JLabel headerLabel3;
	private JButton openBtn;

	public Header (){

		setBackground(Color.GRAY);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	    setPreferredSize(new Dimension(400, 100));

		setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();


	    // Left header

	    left = new JPanel();
	    left.setBackground(Color.GRAY);
	    left.setLayout(new BoxLayout(left, BoxLayout.X_AXIS));

	    left.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	    left.setPreferredSize(new Dimension(200, 100));



		headerLabel1 = new JLabel("", JLabel.CENTER);
		headerLabel1.setMaximumSize(new Dimension(40, 100));
		headerLabel1.setPreferredSize(new Dimension(40, 100));
		headerLabel1.setText("Rows");
		headerLabel1.setForeground(Color.WHITE);

		left.add(headerLabel1);
		left.add(Box.createHorizontalStrut(10));


	    slider = new JSlider(JSlider.HORIZONTAL,10,100,10);
	    slider.setMinorTickSpacing(10);
	    slider.setMajorTickSpacing(10);
	    slider.setPaintTicks(true);
	    slider.setSnapToTicks(true);

	    slider.setMinimumSize(new Dimension(100, 100));
	    slider.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	    slider.setPreferredSize(new Dimension(100, 100));
	    slider.setBackground(Color.GRAY);

	    left.add(slider);
		left.add(Box.createHorizontalStrut(10));


		headerLabel2 = new JLabel("", JLabel.CENTER);
		headerLabel2.setMaximumSize(new Dimension(40, 100));
		headerLabel2.setPreferredSize(new Dimension(40, 100));

		headerLabel2.setText("10");
		headerLabel2.setForeground(Color.WHITE);

		left.add(headerLabel2);



		// Right panel


	    right = new JPanel();
	    right.setLayout(new BoxLayout(right, BoxLayout.X_AXIS));
	    right.setBackground(Color.GRAY);

	    right.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
	    right.setPreferredSize(new Dimension(200, 100));


	    headerLabel3 = new JLabel("", JLabel.CENTER);
		headerLabel3.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		headerLabel3.setPreferredSize(new Dimension(40, 100));

		headerLabel3.setText("File");
		headerLabel3.setForeground(Color.WHITE);

		right.add(headerLabel3);
		right.add(Box.createHorizontalStrut(10));


        openBtn = new JButton("Open");

	    right.add(openBtn);
		right.add(Box.createHorizontalStrut(10));



	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
		add(left, gbc);


	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 1;
	    gbc.gridy = 0;
		gbc.weighty = 1.0;
		add(right, gbc);
	}

	public JSlider getSlider (){
		return slider;
	}

	public void setText (String str){
		headerLabel2.setText(str);
	}

	public JButton getOpenButton (){
		return openBtn;
	}

}
