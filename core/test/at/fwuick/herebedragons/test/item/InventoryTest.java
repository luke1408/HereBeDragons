package at.fwuick.herebedragons.test.item;

import junit.framework.TestCase;

import org.junit.Test;

import at.fwuick.herebedragons.item.*;

public class InventoryTest extends TestCase{
	
	private Inventory testInventory;
	
	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
		testInventory = new Inventory();
		
	}

	@Test
	public void testRows(){
		assertEquals(testInventory.rows(), testInventory.DEFAULT_ROWS);
	}
	
	@Test
	public void testColumns(){
		assertEquals(testInventory.columns(), testInventory.DEFAULT_COLUMNS);
	}
}
