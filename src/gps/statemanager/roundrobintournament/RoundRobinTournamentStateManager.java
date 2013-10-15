package gps.statemanager.roundrobintournament;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import gps.Range;
import gps.statemanager.LocalStateManager;

public class RoundRobinTournamentStateManager extends LocalStateManager {
	private int rounds;
	private int matchesInRound;

	public RoundRobinTournamentStateManager(int size, Range domain) {
		super(size, domain);
		
		if ((size & 1) != 0) { // odd
			size++;
			this.domain = new Range(domain.begin(), domain.end() + 1);
		}
		
		rounds = size - 1;
		matchesInRound = size / 2;
		this.size = size * rounds; // since common size is not correct
	}

	public ArrayList<Integer> getInitialState() {
		ArrayList<Integer> state = new ArrayList<>(size);
		for (int r = 0; r < rounds; r++) { // for each round
			ArrayList<Integer> leftovers = new ArrayList<>();
			for (int i = domain.begin(); i <= domain.end(); i++) {
				leftovers.add(i);
			}
			
			while (leftovers.size() > 0) {
				int number = leftovers.remove(getRandomInt(0,
						leftovers.size() - 1));

				state.add(number);
			}
		}

		return state;
	}

	@Override
	public ArrayList<Integer> getRandomNeighbour(ArrayList<Integer> state) {
		// TODO Automaticky generovaný stub metody
		return null;
	}

	@Override
	public ArrayList<Integer> getInteligentNeighbour(ArrayList<Integer> state,
			int variable) {
		// TODO Automaticky generovaný stub metody
		return null;
	}

	@Override
	public double evaluateState(ArrayList<Integer> state) {
		// TODO Automaticky generovaný stub metody
		return 0;
	}

	@Override
	public void printState(ArrayList<Integer> state) {
		System.out.println(state);
		for (int r = 0; r < rounds; r++) {
			for (int i = 0; i < matchesInRound; i += 1) {
				System.out.print(state.get(r * matchesInRound*2 + i*2));
				System.out.print("-");
				System.out.print(state.get(r * matchesInRound*2 + i*2 + 1));
				System.out.print(" ");
			}
			System.out.println();
		}
	}

	@Override
	public ArrayList<Integer> getConflicts(ArrayList<Integer> state) {
		// TODO Automaticky generovaný stub metody
		return null;
	}

}