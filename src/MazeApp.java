import javax.swing.UIManager;

public class MazeApp {

	public static void main (String[] args){

		try {
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}


		App a = new App();
		a.setVisible(true);
	}

}
