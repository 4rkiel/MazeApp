import java.awt.*;
import javax.swing.*;

public class Footer extends JPanel {

	private static final long serialVersionUID = 1L;

	JLabel footerLabel;

	public Footer (){

		setBackground(Color.GRAY);
		setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));

		setLayout(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();

	    gbc.fill = GridBagConstraints.HORIZONTAL;

		footerLabel = new JLabel("", JLabel.CENTER);
		footerLabel.setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
		footerLabel.setPreferredSize(new Dimension(400, 100));

		footerLabel.setText("No file selected");
		footerLabel.setForeground(Color.WHITE);


		add(footerLabel);

	}


	public void setText (String str){
		footerLabel.setText(str);
	}
}
