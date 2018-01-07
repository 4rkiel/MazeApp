import java.awt.*;
import javax.swing.*;

public class App extends JFrame {

	private static final long serialVersionUID = 1L;

	private Global g;

	public App (){

		String path = System.getProperty("user.dir");

		Toolkit kit = Toolkit.getDefaultToolkit();
		Image img = kit.createImage(path+"/lab.png");
		setIconImage(img);

		setTitle("Hello");
		setMinimumSize(new Dimension(400, 500));
		g = new Global();
		add(g);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}