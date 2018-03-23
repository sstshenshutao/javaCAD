/**
 * 
 */
package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import controller.GeometricsFactory;
import controller.MainController;
import util.Constants;

/**
 * @author Shutao Shen
 *
 */
public class ButtonFactory {

	public static JButton makeGeometricButton(String classname, int interaction){
		String jTitel = classname.substring(classname.lastIndexOf(".")+1,classname.lastIndexOf("Element"));
		Icon jIcon=null;
		try {jIcon=new ImageIcon(jTitel+".ico");}	catch (Exception e) {jIcon=null;}
		System.out.println(jIcon.toString());
		JButton jButton = null;
//		setToolTipText("点击确定")
				
		if (jIcon!=null) {jButton=new JButton(jIcon);}else {jButton=new JButton(jTitel);}
		
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(interaction, classname);
			}
		});
		return jButton;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
