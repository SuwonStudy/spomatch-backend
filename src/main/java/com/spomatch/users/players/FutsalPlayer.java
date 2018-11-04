package com.spomatch.users.players;

import java.util.ArrayList;
import java.util.List;

import com.spomatch.users.common.SportsType;
import com.spomatch.users.parties.FutsalParty;
import com.spomatch.users.teams.FutsalTeam;

public class FutsalPlayer extends Player {

	private FutsalPosition preferredPosition;
	private List<FutsalParty> belongingParties = new ArrayList<>();
	private List<FutsalTeam> belongingTeams = new ArrayList<>();
	
	@Override
	protected Position getPreferredPosition() {
		return preferredPosition;
	}

	@Override
	protected List<FutsalParty> getBelongingParties() {
		return belongingParties;
	}

	@Override
	protected List<FutsalTeam> getBelongingTeams() {
		return belongingTeams;
	}

	@Override
	public FutsalParty createParty(String name) {
		FutsalParty created = new FutsalParty(this, name);
		belongingParties.add(created);
		return created;
	}

	@Override
	public FutsalTeam createTeam(String name) {
		FutsalTeam created = new FutsalTeam(this, name);
		belongingTeams.add(created);
		return created;
	}

	@Override
	public SportsType getSportsType() {
		return SportsType.FUTSAL;
	}

}
