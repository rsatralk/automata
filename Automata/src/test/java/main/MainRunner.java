package main;

import java.io.IOException;

import org.testng.TestNG;

import framework.CreateXml;

public class MainRunner {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		TestNG testng= new TestNG();
		CreateXml createAndRun = new  CreateXml(testng);
		createAndRun.createAndRun();

	}

}
