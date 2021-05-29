package com.java.board.dto;

public class MapDto {
	
	private int mapNo;
	private String xAxis;
	private String yAxis;
	
	public MapDto() {
		super();
	}
	
	public MapDto(int mapNo, String xAxis, String yAxis) {
		super();
		this.mapNo = mapNo;
		this.xAxis = xAxis;
		this.yAxis = yAxis;
	}
	
	@Override
	public String toString() {
		return "MapDto [mapNo=" + mapNo + ", xAxis=" + xAxis + ", yAxis=" + yAxis + "]";
	}
	
	public int getMapNo() {
		return mapNo;
	}
	public void setMapNo(int mapNo) {
		this.mapNo = mapNo;
	}
	public String getxAxis() {
		return xAxis;
	}
	public void setxAxis(String xAxis) {
		this.xAxis = xAxis;
	}
	public String getyAxis() {
		return yAxis;
	}
	public void setyAxis(String yAxis) {
		this.yAxis = yAxis;
	}
}