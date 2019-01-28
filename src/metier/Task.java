package metier;

import java.util.TimerTask;

public class Task extends TimerTask {
	private Game game;
	public Task(Game game) {
		this.setGame(game);
	}
	public void run() {
		int[][] m = this.getGame().getM();
		for(int i=0;i<m.length;i++) {
			for(int j=0;j<m[0].length;j++) {
				if((i!=this.getGame().getKaito().getX() || j!=this.getGame().getKaito().getY()) && (j!=this.getGame().getGoal().getX() || i!=this.getGame().getGoal().getY()))m[i][j]=(Math.random()<0.7)?0:1;
			}
		}
		this.getGame().setM(m);
	}
	public Game getGame() {
		return game;
	}
	public void setGame(Game game) {
		this.game = game;
	}
}
