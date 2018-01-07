import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Laby extends JPanel {

	private static final long serialVersionUID = 1L;

	private String labStr = "";
	private String tmpStr = "";

	private int width = 0;
	private int height = 0;
	private int sizeX = 0;
	private int sizeY = 0;

	private int start = -1;
	private int end = -1;

	private boolean found;
	private int best;

	private int [] list;


	Laby (String str, int w, int h){

		labStr = str;
		width = w;
		height = h;

		int len = labStr.length();
		for(int k = 0; k < len; k++){

			char c = labStr.charAt(k);

			if (c == 'S'){
				start = k;
			} else if (c == 'T'){
				end = k;
			}
		}

		list = new int [width * height];

		sizeX = (int) 200 / width;
		sizeY = (int) 200 / height;

		// TODO GET FRAME SIZE

		//setBackground(Color.LIGHT_GRAY);
		setMaximumSize(new Dimension(202, 202));
	    setPreferredSize(new Dimension(202, 202));

	    if (resolveBest() && best > -1){

	    	if (start < end){
	    		labStr = tmpStr.substring(0, start) + 'S'
	    				+ tmpStr.substring(start+1, end) + 'T'
	    				+ tmpStr.substring(end+1);
	    	} else {
	    		labStr = tmpStr.substring(0, end) + 'T'
	    				+ tmpStr.substring(end+1, start) + 'S'
	    				+ tmpStr.substring(start+1);
	    	}
	    }

	}


	public boolean resolve (){

		if (start == -1 && end == -1){
			return false;
		}

		found = false;
		tmpStr = labStr;

		return resolveAux(start);
	}


	public boolean resolveAux (int k){

		boolean res = false;

		if (tmpStr.charAt(k) == 'T' ){
			found = true;
			res = true;
		}

		if (!found){

			tmpStr = tmpStr.substring(0, k) + 'Z' + tmpStr.substring(k+1);


			if ( ((k % width) > 0)
					&& (tmpStr.charAt(mx(k)) != 'X' )
					&& (tmpStr.charAt(mx(k)) != 'Z' )
				){

				res = res || resolveAux(mx(k));
			}

			if ( ((k % width) < width)
					&& (tmpStr.charAt(px(k)) != 'X' )
					&& (tmpStr.charAt(px(k)) != 'Z' )
				){

				res = res || resolveAux(px(k));
			}

			if ( (k >= width)
					&& (tmpStr.charAt(my(k)) != 'X' )
					&& (tmpStr.charAt(my(k)) != 'Z' )
				){

				res = res || resolveAux(my(k));
			}

			if ( (k < (width * (height - 1)) )
					&& (tmpStr.charAt(py(k)) != 'X' )
					&& (tmpStr.charAt(py(k)) != 'Z' )
				){

				res = res || resolveAux(py(k));
			}

		}

		if (res){
			tmpStr = tmpStr.substring(0, k) + '.' + tmpStr.substring(k+1);
		}



		return res;
	}



	/**************************************************************************
	 *
	 * Resolve Best path
	 *
	 *
	 */

	public boolean resolveBest (){

		if (start == -1 && end == -1){
			return false;
		}

		best = -1;
		tmpStr = labStr;


		return resolveBestAux(start, 0) && traceBest();
	}


	public boolean resolveBestAux (int k, int m){

		boolean res = false;

		if (tmpStr.charAt(k) == 'T' ){

			if (m < best || best == -1){

				res = true;
				best = m;
			}

		} else {

			tmpStr = tmpStr.substring(0, k) + 'Z' + tmpStr.substring(k+1);
			list[k] = m;


			if ( ((k % width) > 0)
					&& (tmpStr.charAt(mx(k)) != 'X' )
					&& ((tmpStr.charAt(mx(k)) != 'Z' )
					|| list[mx(k)] > m+1 )
				){

				res = resolveBestAux(mx(k), m+1) || res;
			}

			if ( ((k % width) < width)
					&& (tmpStr.charAt(px(k)) != 'X' )
					&& ((tmpStr.charAt(px(k)) != 'Z' )
					|| list[px(k)] > m+1 )
				){

				res = resolveBestAux(px(k), m+1) || res;
			}

			if ( (k >= width)
					&& (tmpStr.charAt(my(k)) != 'X' )
					&& ((tmpStr.charAt(my(k)) != 'Z' )
					|| list[my(k)] > m+1 )
				){

				res = resolveBestAux(my(k), m+1) || res;
			}

			if ( (k < (width * (height - 1)) )
					&& (tmpStr.charAt(py(k)) != 'X' )
					&& ((tmpStr.charAt(py(k)) != 'Z' )
					|| list[py(k)] > m+1 )
				){

				res = resolveBestAux(py(k), m+1) || res;
			}


		}

		return res;
	}


	/**************************************************************************
	 *
	 * Trace Best path
	 *
	 *
	 */


	public boolean traceBest (){

		if (start == -1 && end == -1){
			return false;
		}

		tmpStr = labStr;

		return traceBestAux(start, 0);
	}


	public boolean traceBestAux (int k, int m){

		boolean res = false;

		if (tmpStr.charAt(k) == 'T'){

			if (m == best){

				res = true;
			}

		} else {

			tmpStr = tmpStr.substring(0, k) + 'Z' + tmpStr.substring(k+1);


			if ( ((k % width) > 0)
					&& (tmpStr.charAt(mx(k)) != 'X' )
					&& ((tmpStr.charAt(mx(k)) != 'Z' )
					|| list[mx(k)] >= m+1 )
				){

				res = traceBestAux(mx(k), m+1) || res;
			}

			if ( ((k % width) < width)
					&& (tmpStr.charAt(px(k)) != 'X' )
					&& ((tmpStr.charAt(px(k)) != 'Z' )
					|| list[px(k)] >= m+1 )
				){

				res = traceBestAux(px(k), m+1) || res;
			}

			if ( (k >= width)
					&& (tmpStr.charAt(my(k)) != 'X' )
					&& ((tmpStr.charAt(my(k)) != 'Z' )
					|| list[my(k)] >= m+1 )
				){

				res = traceBestAux(my(k), m+1) || res;
			}

			if ( (k < (width * (height - 1)) )
					&& (tmpStr.charAt(py(k)) != 'X' )
					&& ((tmpStr.charAt(py(k)) != 'Z' )
					|| list[py(k)] >= m+1 )
				){

				res = traceBestAux(py(k), m+1) || res;
			}

		}


		if (res){
			tmpStr = tmpStr.substring(0, k) + '.' + tmpStr.substring(k+1);
		}


		return res;
	}


	public int px (int k){
		return k + 1;
	}

	public int py (int k){
		return k + width;
	}

	public int mx (int k){
		return k - 1;
	}

	public int my (int k){
		return k - width;
	}



	@Override
	public void paintComponent (Graphics g){

		super.paintComponent(g);

		int len = labStr.length();

		if (len == width * height){

			for (int k = 0; k < width; k++){
				for (int h = 0; h < height; h++){

					int posi = (k * width) + h;

					if (len >= posi){

						g.setColor(Color.darkGray);
						g.drawRect(h*sizeX, k*sizeY, sizeX, sizeY);
						g.drawRect((h*sizeX)+1, (k*sizeY)+1, sizeX-1, sizeY-1);

						char c = labStr.charAt(posi);
						switch(c){

							case 'X':

								g.setColor(Color.LIGHT_GRAY);

							break;

							case ' ':
							case 'Z':

				                g.setColor(Color.WHITE);

							break;

							case '.':

				                g.setColor(Color.CYAN);

							break;

							case 'S':

				                g.setColor(Color.GREEN);

							break;

							case 'T':

				                g.setColor(Color.RED);

							break;

						}


						g.fillRect((h*sizeX)+2, (k*sizeY)+2, sizeX-2, sizeY-2);

					}
				}
			}

			g.setColor(Color.darkGray);
			g.drawLine(201, 0, 201, 201);
			g.drawLine(0, 201, 201, 201);


		}

	}


}
