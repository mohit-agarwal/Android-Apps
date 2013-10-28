package com.example.assignemnt5;

public class GadgetData {

	public String name;
	public String size;
	public String wt;
	public String OS;
	public String connectivity;
	public String processor;
	public String camera;
	public String sd_storage;
	public String img;
	
	

	public GadgetData(String name,String size, String wt,String OS,String connectivity,String processor,String camera,String sd_storage,String img) {
		this.name=name;
		this.wt=wt;
		this.size = size;
		this.OS = OS;
		this.connectivity=connectivity;
		this.processor=processor;
		this.camera=camera;
		this.sd_storage=sd_storage;
		this.img="img"+img;
	}


}