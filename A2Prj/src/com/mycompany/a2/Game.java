package com.mycompany.a2;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label; 
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent; 
import java.lang.String; 
public class Game extends Form{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	
	public Game() {
		gw = new GameWorld();
		mv = new MapView();
		sv = new ScoreView();
		gw.addObserver(mv);
		gw.addObserver(sv);
		gw.init();
		this.setLayout(new BorderLayout());
		Container myContainer = new Container();
		this.add(BorderLayout.CENTER, myContainer);
		Container northContainer = new Container();
		Container southContainer = new Container();
		Container westContainer = new Container();
		Container eastContainer = new Container();
		Container centerContainer = new Container();
		myContainer.setLayout(new BorderLayout());
		myContainer.add(BorderLayout.CENTER, centerContainer);
		centerContainer.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.rgb(255,0,0)));
		Button centerButton = new Button("I am center button.");
		
		Button jumpToAstronautButton = new Button(new JumpToRandomAstronaut(gw));
		Button moveLeftButton = new Button(new MoveLeft(gw));
		Button moveUpButton = new Button(new MoveUp(gw));
		Button expandButton = new Button(new Expand(gw));


		Button northButton = new Button("I am north button.");
		Button southButton = new Button("I am south button test.");
		Button eastButton = new Button("I am east button test.");

		centerContainer.add(centerButton);
		//centerContainer.addComponent(mv);
		myContainer.add(BorderLayout.NORTH, northContainer);
		northContainer.setLayout(new BorderLayout());
		northContainer.add(BorderLayout.SOUTH, southContainer);
		Toolbar toolbar = new Toolbar();
		northContainer.add(BorderLayout.NORTH, toolbar);
		northContainer.add(BorderLayout.SOUTH, northButton);
		myContainer.add(BorderLayout.WEST, westContainer);
		myContainer.add(BorderLayout.EAST, eastContainer);
		myContainer.add(BorderLayout.SOUTH, southContainer);
		
		westContainer.setLayout(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.add(expandButton);
		westContainer.add(moveUpButton);
		westContainer.add(moveLeftButton);
		westContainer.add(jumpToAstronautButton);

		
		eastContainer.add(eastButton);
		southContainer.add(southButton);
		

		this.show();
	}
	
	
//	private void play(){ 
//		Label myLabel=new Label("Enter a Command:"); 
//		this.addComponent(myLabel); 
//		final TextField myTextField=new TextField(); 
//		this.addComponent(myTextField); 
//		this.show(); 
// 
//		myTextField.addActionListener(new ActionListener(){ 
//			public void actionPerformed(ActionEvent evt) { 
//				String sCommand=myTextField.getText().toString(); 
//				myTextField.clear(); 
//				if(sCommand.length() != 0) 
//				switch (sCommand.charAt(0)) { 
//				case 'a':
//					gw.jumpToRandomAlien();
//					break;
//				case 'o':
//					gw.jumpToRandomAstro();
//					break;
//				case 'r':
//					gw.moveSpaceRight();
//					break;
//				case 'l':
//					gw.moveSpaceLeft();
//					break;
//				case 'u':
//					gw.moveSpaceUp();
//					break;
//				case 'd':
//					gw.moveSpaceDown();
//					break;
//				case 'e': 
//					gw.expand(); 
//					break; 
//				case 'c':
//					gw.contract();
//					break;
//				case 't':
//					gw.gameTick();
//					break;
//				case 's':
//					gw.openDoor();
//					break;
//				case 'w':
//					gw.alienClone();
//					break;
//				case 'p':
//					gw.printState();
//					break;
//				case 'f':
//					gw.attack();
//					break;
//				case 'm':
//					gw.map();
//					break;
//				case 'x':
//					System.out.println("Enter y or n if you want to exit:");
//					break;
//				case 'y':
//					System.exit(0);
//					break;
//				case 'n':
//					break;
//				//add code to handle rest of the commands 
//				} //switch 
//			} //actionPerformed 
//		} //new ActionListener() 
//		); //addActionListener 
//	} //play
}
