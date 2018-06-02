package com.henry.ugokwe;


/**
 * @author HENRY
 * 
 */
public interface PartManager {
	public enum PartResponse
	{
	SUCCESS,
	OUT_OF_STOCK,
	NO_LONGER_MANUFACTURED
	}
	// Submit part for manufacture and delivery.
	public PartResponse SubmitPartForManufactureAndDelivery(
	int parNtumber,
	int quantity,
	DeliveryAddress deliveryAddress) ;
}
