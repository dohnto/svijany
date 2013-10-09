package gps.solver;

import gps.Range;
import gps.statemanager.LocalStateManager;
import java.util.ArrayList;

public abstract class MinConflicts extends ConstraintBasedLocalSearch {        
    
    /* PROPERTIES */   
    private int MAX_STEPS = 10000;
    
    /* METHODS */   
    
    /**
     * Constructor
     * @param stateManager
     * @param size number of variables
     * @param values range of allowable values for state varibles
     */     
    public MinConflicts(LocalStateManager stateManager) {
        super(stateManager);
    }	
    
    /**     
     * @param varCount  Number of variables
     * @param range     Allowed range of variables' values
     * @return List of variables with assigned values representing the solution.
     */
    @Override
    public ArrayList solve(int maxIterations) {                                                
        // Initial puzzle state - randomly initializes the variables
        state = stateManager.getInitialState();
        
//        System.err.println("INITIAL STATE:");
//        stateManager.printState(state);
        
        // Main loop
        while (step++ < MAX_STEPS) {                        
            // Get conflicting variables
            ArrayList<Integer> conflicts = stateManager.getConflicts(state);
            
            //System.out.println("Conflicts: " + conflicts);
            
            // Check whether puzzle is solved
            if(conflicts.isEmpty()) break;
            
            // Randomly choose conflicting variable
            int idx  = LocalStateManager.getRandomInt(0, conflicts.size() - 1);
            int c    = conflicts.get(idx);
            
            //System.out.println("Choosing queen " + idx);
            
            // Set new (better) value for the conflicting variable
            state = stateManager.getInteligentNeighbour(state, c);            
        }
        
//        System.err.println("FINAL STATE:");
//        stateManager.printState(state);
        
        return state;
    }            
}
