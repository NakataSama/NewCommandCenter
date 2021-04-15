package com.elo7.newcommandcenter.field;

import java.util.List;

import com.elo7.newcommandcenter.position.Position;
import com.elo7.newcommandcenter.probe.Probe;

public class Field {
	
	private int id;
	private Position position;
	private List<Probe> probes;
	
	public Field() {}
	
	public Field(int id, Position position, List<Probe> probes) {
		this.id = id;
		this.position = position;
		this.probes = probes;
	}
	
	public int getId() {
		return this.id;
	}
	
	public Position getPosition() {
		return this.position;
	}
	
	public List<Probe> getProbes() {
		return this.probes;
	}

	@Override
	public String toString() {
		return new StringBuilder()
				.append("Field ID = ").append(id)
				.append(" | Max X = ").append(position.getX())
				.append(" | Max Y = ").append(position.getY())
				.append(" | Number of Probes = ").append(probes.size()).toString();
	}

	public Field addProbeToField(Probe probe) {
		
		if(!isProbeBeyondFieldLimits(probe))
			throw new RuntimeException("This probe is out of the field bounds");
		
		this.probes.forEach(p -> {
			if(p.getPosition().getX() == probe.getPosition().getX()
				&& p.getPosition().getY() == probe.getPosition().getY())
				throw new RuntimeException("There's a probe in this position! Try another one");
		});
		
		this.probes.add(probe);
		
		return new Field(id, position, probes);
	}
	
	private boolean isProbeBeyondFieldLimits(Probe probe) {
		if(probe.getPosition().getX() > this.position.getX() 
			|| probe.getPosition().getY() > this.position.getY()
			|| probe.getPosition().getX() < 0
			|| probe.getPosition().getY() < 0)
			return false;
		else
			return true;
	}

}
