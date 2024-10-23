package com.mycompany.a2;

import com.codename1.ui.Container;

import java.util.Observable;
import java.util.Observer;


public class MapView extends Container implements Observer{
	private GameWorld gw;
	
	public MapView(GameWorld gw) {
		this.gw = gw;
	}
	
	@Override
	public void update(Observable observable, Object data) {
		gw.map();
	}
}
