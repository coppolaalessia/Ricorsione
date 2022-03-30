package it.polito.tdp.ricorsione.model;

import java.util.ArrayList;
import java.util.List;

public class Regine {
	
	private List<List<Integer>> tutte ;

	//METODO GENERALE --> serve per la prima ricorsione
	public List<List<Integer>> cercaRegine(int N) {
		
		this.tutte = new ArrayList<List<Integer>>();
		List<Integer> parziale = new ArrayList<Integer>() ;
		
		regine_ricorsiva(parziale, 0, N);	
		return this.tutte ;
	}
	
	//METODO RICORSIVO che parte da una soluzione parziale e deve conoscere il livello 
	//della ricorsione e la dimensione della scacchiera
	
	private void regine_ricorsiva(List<Integer> parziale, int livello, int N ) {
		
		if(livello==N) { // CASO TERMINALE (ho trovato una soluzione completa, cioè livello=N)
//			System.out.println(parziale);
			this.tutte.add(new ArrayList<Integer>(parziale));
		} else {
			// ho già parziale[0] fino a parziale[livello-1] già decise
			// devo decidere parziale[livello] tra tutti i valori possibili
			// da 0 a N-1 (colonne), purché compatibili
			for(int col=0; col<N; col++) {
				if(compatibile(livello, col, parziale)) {
					
					parziale.add(col);  // provo a mattere una regina
					regine_ricorsiva(parziale, livello+1, N);
					parziale.remove(parziale.size()-1); // BACKTRACKING -->tolgo la regina appena 
														//messa per provare le altre regine possibili 
														//allo stesso livello
				}
			}
		}
	}
	
	private boolean compatibile(int livello, Integer col, List<Integer> parziale) {
		if (parziale.indexOf(col) != -1) // se trovo già occupata quella colonna
			return false;
		
		//verifica diagonali: controllo tutte le righe dove ci sono gia delle regine
		for(int riga = 0; riga < parziale.size(); riga++ )  {
			// regina alle coordinate (R,C)=( riga, parziale.get(riga) )
			// confrontare con (R,C)=(livello, col)
			if(riga + parziale.get(riga) == livello+col)
				return false;
			if(riga - parziale.get(riga) == livello-col)
				return false;
		}
		return true ;
	}
	
}
