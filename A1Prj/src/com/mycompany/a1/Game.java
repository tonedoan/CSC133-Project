package com.mycompany.a1;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Form;
import com.codename1.ui.Label; 
import com.codename1.ui.TextField; 
import com.codename1.ui.events.ActionEvent; 
import java.lang.String; 
public class Game extends Form{
	private GameWorld gw;
	
	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}
	
	private void play(){ 
		Label myLabel=new Label("Enter a Command:"); 
		this.addComponent(myLabel); 
		final TextField myTextField=new TextField(); 
		this.addComponent(myTextField); 
		this.show(); 
 
		myTextField.addActionListener(new ActionListener(){ 
			public void actionPerformed(ActionEvent evt) { 
				String sCommand=myTextField.getText().toString(); 
				myTextField.clear(); 
				if(sCommand.length() != 0) 
				switch (sCommand.charAt(0)) { 
				case 'a':
					gw.jumpToRandomAlien();
					break;
				case 'o':
					gw.jumpToRandomAstro();
					break;
				case 'r':
					gw.moveSpaceRight();
					break;
				case 'l':
					gw.moveSpaceLeft();
					break;
				case 'u':
					gw.moveSpaceUp();
					break;
				case 'd':
					gw.moveSpaceDown();
					break;
				case 'e': 
					gw.expand(); 
					break; 
				case 'c':
					gw.contract();
					break;
				case 't':
					gw.gameTick();
					break;
				case 's':
					gw.openDoor();
					break;
				case 'w':
					gw.alienClone();
					break;
				case 'p':
					gw.printState();
					break;
				case 'f':
					gw.attack();
					break;
				case 'm':
					gw.map();
					break;
				case 'x':
					System.out.println("Enter y or n if you want to exit:");
					break;
				case 'y':
					System.exit(0);
					break;
				case 'n':
					break;
				//add code to handle rest of the commands 
				} //switch 
			} //actionPerformed 
		} //new ActionListener() 
		); //addActionListener 
	} //play
}
