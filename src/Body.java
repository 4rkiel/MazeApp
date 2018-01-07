import java.awt.*;
import javax.swing.*;

public class Body extends JPanel {

	private static final long serialVersionUID = 1L;

	public Body() {

		int k = 10 * 255 / 100;

		Color c = new Color(k, k, k);

		setBackground(c);

		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.fill = GridBagConstraints.BOTH;

	}

	void changeColor(int x) {

		int k = x * 255 / 100;

		Color c = new Color(k, k, k);

		setBackground(c);
	}
}
