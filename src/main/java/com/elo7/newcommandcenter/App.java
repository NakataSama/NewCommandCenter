package com.elo7.newcommandcenter;

import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.probe.Probe;

public class App 
{
    public static void main( String[] args ) {
    	Probe probe = new Probe(new Position(1, 1), Orientation.NORTH, 0);
    	Probe probe2 = probe;
    	
    	System.out.println(probe.rotate("L").move());
        System.out.println(probe2.rotate("R").move());
    }
}
