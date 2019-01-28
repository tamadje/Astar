package metier;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.Timer;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import entities.Kaito;
import entities.Map;
import entities.Node;

public class Game extends JPanel implements MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Game game;
	private List<Node> path;
	private Node goal;
	private Kaito kaito;
	private Map map;
	private boolean start = false;
	int[][] m;

	private Game() {
		Timer timer = new Timer();
		Task task = new Task(this);
		
	    JTextField xField = new JTextField(4);
	    JTextField yField = new JTextField(4);
	    JPanel myPanel = new JPanel();
	    int result;
    	myPanel.add(new JLabel("L (max = 25 , min = 5) :"));
	    myPanel.add(xField);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(new JLabel("C (max = 25 , min = 5) :"));
	    myPanel.add(yField);
	    xField.setText("5");
	    yField.setText("5");
	    int x=5,y=5;
	    do{
		    result = JOptionPane.showConfirmDialog(null, myPanel, "Please Enter C and L Values", JOptionPane.OK_CANCEL_OPTION);
			x = Integer.parseUnsignedInt(xField.getText());
			y = Integer.parseUnsignedInt(yField.getText());
	    }while(result != JOptionPane.OK_OPTION || x>25 || x<=4 || y>25 || y<=4);
        String[] possibleValues = { "Empty", "Auto", "Dynamique" };
        Object selectedValue = JOptionPane.showInputDialog(null,
                "Choose one", "Input",
                JOptionPane.INFORMATION_MESSAGE, null,
                possibleValues, possibleValues[0]);
        System.out.println(selectedValue);
		m = new int[x][y];
		setPreferredSize(new Dimension(m[0].length * 32, m.length * 32));
		addMouseListener(this);
		kaito = new Kaito(0, 0);
		goal = new Node(0, 0, true);
		map = new Map(m);
		if(selectedValue.equals("Dynamique")) {
			timer.scheduleAtFixedRate(task, 0, 5000);	
		}
		else if(selectedValue.equals("Auto")) {
			task.run();	
		}

	}
	
	public static Game getGame() {
		if(game == null) {
			game = new Game();
		}
		return game;
	}
	
	public void update() {
		kaito.update();
	}
	
	public void render(Graphics2D g) {
		map.drawMap(g, path);
		g.setColor(Color.GRAY);
		for (int x = 0; x < getWidth(); x += 32)
		{
			g.drawLine(x, 0, x, getHeight());
		}
		for (int y = 0; y < getHeight(); y += 32)
		{
			g.drawLine(0, y, getWidth(), y);
		}
		
		g.setColor(Color.BLACK);
		g.fillRect(kaito.getX() * 32 + kaito.getSx(), kaito.getY() * 32 + kaito.getSy(), 32, 32);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int buttonDown = e.getButton();
		int mx = e.getX() / 32;
		int my = e.getY() / 32;
		if(mx != goal.getX() || my != goal.getY()) {
			if(buttonDown == MouseEvent.BUTTON1) {
				start = true;
				goal.setX(mx);
				goal.setY(my);
			}
			if(buttonDown == MouseEvent.BUTTON3) {
		           // Bouton DROIT enfoncé
				m[my][mx] = (m[my][mx]+1)%2;
				map = new Map(m);
		    }
			if (start) {
				if (map.getNode(goal.getX(), goal.getY()).isWalkable())
				{
					path = map.findPath(kaito.getX(), kaito.getY(), goal.getX(), goal.getY());
					kaito.followPath(path);
				}
				else
				{
					System.out.println("Can't walk to that node!");
				}
			} 
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public Node getGoal() {
		return goal;
	}

	public void setGoal(Node goal) {
		this.goal = goal;
	}

	public Kaito getKaito() {
		return kaito;
	}

	public void setKaito(Kaito kaito) {
		this.kaito = kaito;
	}

	public int[][] getM() {
		return m;
	}

	public void setM(int[][] m) {
		this.m = m;
		map = new Map(m);
		path = map.findPath(kaito.getX(), kaito.getY(), goal.getX(), goal.getY());
		kaito.followPath(path);
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	
}
