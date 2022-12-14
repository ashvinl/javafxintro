import java.util.ArrayList;

public class P1_Loghashankar_Ashvin_LifeModel extends GridModel<Boolean>{
	
	ArrayList<GenerationListener> arr;
	int generationNumber = 0;
	
	public P1_Loghashankar_Ashvin_LifeModel(Boolean[][] gridData) {
		super(gridData);
		arr = new ArrayList<GenerationListener>();
	}
	
	public void runLife(int numGeneratrions) {
		for(int i = 0; i<numGeneratrions; i++) {
			nextGeneration();
		}
	}
	
	public int rowCount(int row) {
		if(row < 0 || row >= getNumRows()) return -1;
		int sum = 0;
		for(int i = 0; i<getNumCols(); i++) {
			if(getValueAt(row,i)) sum++;
		}
		return sum;
	}
	
	public int colCount(int col) {
		if(col < 0 || col >= getNumCols()) return -1;
		int sum = 0;
		for(int i =0; i<getNumRows(); i++) {
			if(getValueAt(i,col)) sum++;
		}
		return sum;
	}
	
	public int totalCount() {
		int sum = 0;
		for(int i = 0; i<getNumRows(); i++) {
			sum += rowCount(i);
		}
		return sum;
	}
	
	/*public void printBoard() {
		int cellsAlive = 0;
		System.out.print(" ");
		for(int i = 0; i<getNumRows(); i++) {
			System.out.printf("%2d", i%10);
		}
		System.out.println();
		for(int i = 0; i<getNumRows(); i++) {
			System.out.printf("%2d", i);
			for(int j = 0; j<getNumRows(); j++) {
				if(getValueAt(i,j)) {
					System.out.print(" *");
					cellsAlive++;
				}
				else System.out.print("  ");
			}
			System.out.println();
		}
		
		int numCellsInNine = 0;
		for(int i = 0; i<arr[9].length; i++) {
			if(arr[9][i]) numCellsInNine++;
		}
		System.out.println("Number of living cells in row 9 --> " + numCellsInNine);
		
		numCellsInNine = 0;
		for(int i = 0; i<arr.length; i++) {
			if(arr[i][9]) numCellsInNine++;
		}
		System.out.println("Number of living cells in col 9 --> " + numCellsInNine);
		
		System.out.println("Number of living cells total --> " + cellsAlive);
		
	}*/
	
	public void nextGeneration() {
		Boolean[][] isAlive = new Boolean[getNumRows()][getNumCols()];
		for(int i = 0; i<getNumRows(); i++) {
			for(int j = 0; j<getNumCols(); j++) {
				int numNeighbors = getNumNeighbors(i,j);
				if(getValueAt(i,j) && (numNeighbors==2 || numNeighbors==3)) {
					isAlive[i][j] = true;
					continue;
				}
				if(getValueAt(i,j)) {
					isAlive[i][j] = false;
					continue;
				}
				if(numNeighbors == 3) {
					isAlive[i][j] = true;
				}
				else {
					isAlive[i][j] = false;
				}
			}
		}
		
		for(int i = 0; i<getNumRows(); i++) {
			for(int j = 0; j<getNumCols(); j++) {
				setValueAt(i,j,isAlive[i][j]);
			}
		}
		setGrid(isAlive);
	}
	
	public int getNumNeighbors(int a, int b) {
		int num = 0;
		int row;
		int col;
		
		row = a-1;
		col = b-1;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a;
		col = b-1;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a-1;
		col = b;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a-1;
		col = b+1;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a+1;
		col = b-1;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a+1;
		col = b;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a+1;
		col = b+1;
		if(isAlive(row,col)) {
			num++;
		}
		
		row = a;
		col = b+1;
		if(isAlive(row,col)) {
			num++;
		}
		return num;
	}
	
	public boolean isAlive(int a, int b) {
		if(a<0 || a>=getNumRows()) return false;
		if(b<0 || b>=getNumCols()) return false;
		return getValueAt(a,b);
	}
	
	public void addGenerationListener(GenerationListener l) {
		arr.add(l);
	}
	
	public void removeGenerationListener(GenerationListener l) {
		arr.remove(l);
	}
	
	public void setGeneration(int gen) {
		for(GenerationListener l: arr) {
			l.generationChanged(generationNumber, gen);
		}
		generationNumber = gen;
	}
	
	public int getGeneration() {
		return generationNumber;
	}

}
