package com.henry.ugokwe;

public class Item {
	

	public int getPartNumber() {
		return partNumber;
	}



	public void setPartNumber(int partNumber) {
		this.partNumber = partNumber;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}



	int partNumber;
	int quantity;

	

	/*
		To maintain the blackbox principle we will ask the Item object itself
		whether it is valid, this allows users of the class to be ignorant of the
		internal storage / implementation of the Item class.  For example, the Item
		class could store the uniqueid and quantity in a dictionary, as individual class
		members (as they are here), the details are private to Item.
	*/
	 public boolean validate()
	{
		// Items are valid if the uniqueID is an integer, quantity is an integer,
		// quantity is > 0.
		try
		{
			int partNumberValue = (partNumber);
			if(partNumberValue <= 0){
				return false;
			}
		}
		catch (NumberFormatException e)
		{
			// The uniqueID member variable is not an integer.
			return false;
		}
		catch (NullPointerException e)
		{
			// The uniqueID member variable is null.
			return false;
		}
		try
		{
			int quantityValue = (quantity);
			if (quantityValue <= 0)
			{
				return false;
			}
		}
		catch (NumberFormatException e)
		{
			// Quanity is not an integer.
			return false;
		}
		catch (NullPointerException e)
		{
			// Quantity is null.
			return false;
		}
		return true;
	}



	public Item(int partNumber, int quantity) {
		this.partNumber = partNumber;
		this.quantity = quantity;
	}
	public Item(){
		
	}
}
