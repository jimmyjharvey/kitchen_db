package kitchen;

import java.awt.EventQueue;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import java.awt.Font;

public class ShoppingList {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingList window = new ShoppingList();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws SQLException 
	 */
	public ShoppingList() throws SQLException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws SQLException 
	 */
	private void initialize() throws SQLException {
		frame = new JFrame();
		frame.setBounds(100, 100, 350, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(24, 64, 289, 325);
		frame.getContentPane().add(editorPane);
		
		JLabel lblShoppingList = new JLabel("Shopping List");
		lblShoppingList.setFont(new Font("Times New Roman", Font.BOLD, 30));
		lblShoppingList.setBounds(76, 11, 190, 42);
		frame.getContentPane().add(lblShoppingList);
		
		/*		
		 * SELECT FoodName, SUM(Qty)-AVG(InventoryQty)+AVG(MinQty) AS QtyNeeded, StandardMeasure
		 * FROM MEALPLAN M, PLANRECIPE P, INGREDIENTS I, FOOD F 
		 * WHERE F.FoodID=I.FoodID 
		 *   AND P.RecipeID=I.RecipeID 
		 *   AND M.PlanID=P.PlanID 
		 *   AND MealDate >= DATE(NOW()) 
		 *   AND MealDate <= DATE(NOW() + INTERVAL 14 DAY) 
		 *   GROUP BY F.FoodID 
		 *   HAVING (SUM(Qty)-AVG(InventoryQty)+AVG(MinQty)) > 0;
		*/
		ResultSet shoppingListQuery = DBConn.getResults("SELECT FoodName, SUM(Qty)-AVG(InventoryQty)+AVG(MinQty) AS QtyNeeded, StandardMeasure FROM MEALPLAN M, PLANRECIPE P, INGREDIENTS I, FOOD F WHERE F.FoodID=I.FoodID AND P.RecipeID=I.RecipeID AND M.PlanID=P.PlanID AND MealDate >= DATE(NOW()) AND MealDate <= DATE(NOW() + INTERVAL 14 DAY) GROUP BY F.FoodID HAVING (SUM(Qty)-AVG(InventoryQty)+AVG(MinQty)) > 0;");
	
		StringBuilder sb = new StringBuilder("");
		while(shoppingListQuery.next()){
			int qtyNeeded = (int) Double.parseDouble(shoppingListQuery.getString("QtyNeeded"));
			sb.append(qtyNeeded + " " + shoppingListQuery.getString("StandardMeasure") + "\t" + shoppingListQuery.getString("FoodName") + "\n");
		}
		
		editorPane.setText(sb.toString());
	}
}
