package com.mycompany.a2;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;

public class MapView extends Container implements Observer{
	@Override
	public void update(Observable observable, Object data) {
		if(observable instanceof GameWorld) {
			GameWorld gw = (GameWorld) observable;
			gw.map();
		}
	}
}
