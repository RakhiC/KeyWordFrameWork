package delta.main;

import generics.Excel;

public class ExcelCode {

	

	public void testController(String ExecutionStatus) throws InterruptedException
	{
		String scenariosPath ="D:/Rakhi/Framework/Delta/scripts/Controller.xlsx";
		String scenarioSheet = "Suite";
		
		int stepCount = Excel.getRowCount(scenariosPath, scenarioSheet);
		for (int i = 1; i <= stepCount; i++) {
			
			String description=Excel.getCellValue(scenariosPath, scenarioSheet, i, 0);
			String action=Excel.getCellValue(scenariosPath, scenarioSheet, i, 1);
			String input1=Excel.getCellValue(scenariosPath, scenarioSheet, i, 2);
			String input2=Excel.getCellValue(scenariosPath, scenarioSheet, i, 3);
		//	String msg="Description: "+description+" Action: "+action+" Input1: "+input1+" Input2: "+input2;
			System.out.println(description+action+input1+input2);
			
		}
		
		}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
