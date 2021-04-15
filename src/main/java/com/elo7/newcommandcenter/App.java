package com.elo7.newcommandcenter;

import java.util.ArrayList;

import com.elo7.newcommandcenter.field.Field;
import com.elo7.newcommandcenter.orientation.Orientation;
import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.probe.Probe;

public class App 
{
    public static void main( String[] args ) {
    	Field field = new Field(1, new Position(10, 10) , new ArrayList<Probe>());
    	Probe probe = new Probe(1, new Position(1, 1), Orientation.NORTH, 0, field);
    	Probe probe2 = probe.rotate("l").move();
    	
    	field.addProbeToField(probe);
    	field.addProbeToField(probe2);
    	
    	System.out.println(field);
    	field.getProbes().forEach(p -> System.out.println(p));
    }
}
