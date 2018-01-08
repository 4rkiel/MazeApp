import java.awt.Color;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;

public class Global extends JPanel {

	private static final long serialVersionUID = 1L;

	private Header head;
	private Body wrapper;
	private Footer foo;

	private Laby lab;

	private int load = 0;
	private int opti = 0;
	private int path = 0;

	public Global() {

		setBackground(Color.WHITE);
		setSize(new Dimension(400, 500));

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		head = new Header();
		wrapper = new Body();
		foo = new Footer();

		JSlider s = head.getSlider();
		s.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				int val = ((JSlider) e.getSource()).getValue();
				wrapper.changeColor(val);
			}
		});

		JButton openBtn = head.getOpenButton();
		openBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				FileDialog dialog = new FileDialog((Frame) null, "Open");
				dialog.setMode(FileDialog.LOAD);
				dialog.setVisible(true);
				String file = dialog.getFile();
				if (file == null) {

					foo.setText("No file selected.");
					load = 0;
					wrapper.removeAll();
					wrapper.revalidate();
					repaint();


				} else {

					try {

						File f = new File(file);
						FileReader fileReader = new FileReader(f);
						BufferedReader bufferedReader = new BufferedReader(fileReader);
						String line;

						line = bufferedReader.readLine();

						if (line.equals("<! laby file />")) {

							line = bufferedReader.readLine();

							int width = 0;
							width = Integer.parseInt(line);

							line = bufferedReader.readLine();

							int height = 0;
							height = Integer.parseInt(line);

							if (width > 0 && height > 0) {

								StringBuffer stringBuffer = new StringBuffer();

								while ((line = bufferedReader.readLine()) != null) {
									stringBuffer.append(line);
								}

								fileReader.close();

								String labStr = stringBuffer.toString();

								lab = new Laby(labStr, width, height);

								foo.setText(file);
								load = 1;

								wrapper.removeAll();
								wrapper.revalidate();
								wrapper.add(lab);

							} else {
								foo.setText("No file selected.");
								fileReader.close();
								load = 0;
								wrapper.removeAll();
								wrapper.revalidate();
								repaint();

							}

						} else {
							foo.setText("No file selected.");
							fileReader.close();
							load = 0;
							wrapper.removeAll();
							wrapper.revalidate();
							repaint();

						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});

		JButton pathBtn = head.getPathButton();
		pathBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if (load == 1){
					if (path == 0){

						if (opti == 1){
							lab.restore();
							opti = 0;
						}

						lab.resolve();
						path = 1;

					} else {

						lab.restore();
						opti = 0;
						path = 0;
					}

					repaint();
				}
			}
		});

		JButton bestBtn = head.getBestButton();
		bestBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				if(load == 1){
					if (opti == 0){

						if (path == 1){
							lab.restore();
							path = 0;
						}

						lab.resolveBest();
						opti = 1;

					} else {

						lab.restore();
						opti = 0;
						path = 0;
					}

					repaint();
				}
			}
		});


		add(head);
		add(wrapper);
		add(foo);

	}
}
