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
				head.setText("" + val);
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
					// TODO CLEAN SCENE

				} else {

					foo.setText(file);

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

								wrapper.removeAll();
								wrapper.revalidate();
								wrapper.add(lab);

								// TODO DISPLAY LABY

							} else {
								fileReader.close();
							}

						} else {
							fileReader.close();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}

				}
			}
		});

		add(head);
		add(wrapper);
		add(foo);

	}
}
