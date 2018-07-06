package com.Proyecto.game.Entities;

import java.util.ArrayList;

public class StringBlocks {

	private ArrayList<Blocks> blocks = new ArrayList<Blocks>();
	
	public void addBlock(Blocks block) {
		blocks.add(block);
	}
	
	public boolean gameEnds(float y) {
		for (Blocks b: blocks) {			
			if (b.getBodyp() > y * 1.8f) {
				return true;
			}
		}
		return false;
	}

	public void dispose(){
		for (Blocks b:blocks){
			b.dispose();
		}
	}
	
}
