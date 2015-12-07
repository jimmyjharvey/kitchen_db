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

	private JFrame frmShoppingList;
	
	public JFrame getForm(){
		return frmShoppingList;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ShoppingList window = new ShoppingList();
					window.frmShoppingList.setVisible(true);
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
		frmShoppingList = new JFrame();
		frmShoppingList.setTitle("Shopping List");
		frmShoppingList.setBounds(100, 100, 290, 360);
		frmShoppingList.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmShoppingList.getContentPane().setLayout(null);
		
		JEditorPane editorPane = new JEditorPane();
		editorPane.setBounds(10, 11, 250, 300);
		frmShoppingList.getContentPane().add(editorPane);
		
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
		ResultSet shoppingListQuery = DBConn.getResults("SELECT FoodName, PreferredStore, SUM(Qty)-AVG(InventoryQty)+AVG(MinQty) AS QtyNeeded, StandardMeasure FROM MEALPLAN M, PLANRECIPE P, INGREDIENTS I, FOOD F WHERE F.FoodID=I.FoodID AND P.RecipeID=I.RecipeID AND M.PlanID=P.PlanID AND MealDate >= DATE(NOW()) AND MealDate <= DATE(NOW() + INTERVAL 14 DAY) GROUP BY F.FoodID HAVING (SUM(Qty)-AVG(InventoryQty)+AVG(MinQty)) > 0;");

		StringBuilder CostcoSB = new StringBuilder("Costco\n");
		StringBuilder HarrisTeeterSB = new StringBuilder("Harris Teeter\n");
		StringBuilder ShoppersSB = new StringBuilder("Shoppers\n");
		while(shoppingListQuery.next()){
			int qtyNeeded = (int) Double.parseDouble(shoppingListQuery.getString("QtyNeeded"));
			if(shoppingListQuery.getString("PreferredStore").equals("2")){
				CostcoSB.append(qtyNeeded + " " + shoppingListQuery.getString("StandardMeasure") + "\t" + shoppingListQuery.getString("FoodName") + "\n");
			} else if (shoppingListQuery.getString("PreferredStore").equals("1")) {
				ShoppersSB.append(qtyNeeded + " " + shoppingListQuery.getString("StandardMeasure") + "\t" + shoppingListQuery.getString("FoodName") + "\n");
			} else {
				HarrisTeeterSB.append(qtyNeeded + " " + shoppingListQuery.getString("StandardMeasure") + "\t" + shoppingListQuery.getString("FoodName") + "\n");
			}
		}
		
		editorPane.setText(CostcoSB.toString() + "\n\n" + HarrisTeeterSB.toString() + "\n\n" + ShoppersSB.toString() + "\n\n");
	}
}
