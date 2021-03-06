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
		System.out.println(jIcon);
		JButton jButton = null;	
		if (jIcon!=null) {jButton=new JButton(jIcon);}else {jButton=new JButton(jTitel);}
		jButton.setToolTipText(new String("draw a "+jTitel));
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(interaction, classname);
			}
		});
		return jButton;
	}
	public static JButton makeChangeButton(String opName, int interaction){
		String jTitel = opName;
		Icon jIcon=null;
		try {jIcon=new ImageIcon(jTitel+".ico");}	catch (Exception e) {jIcon=null;}
		System.out.println(jIcon.toString());
		JButton jButton = null;	
		if (jIcon!=null) {jButton=new JButton(jIcon);}
		jButton.setToolTipText(new String(jTitel));
		jButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// inform the controller of the user interaction
				MainController.getInstance().setUserInput(interaction, jTitel);
			}
		});
		return jButton;
	}


}
