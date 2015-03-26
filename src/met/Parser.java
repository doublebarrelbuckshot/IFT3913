package met;


import java.io.*;
import java.util.*;

import javax.swing.JOptionPane;


public class Parser {
	private static GUI gui;

	public static Model launch(String fileName, GUI guix) {
		gui = guix;
		BufferedReader br= null;
		String everything = "";
		try {
			br = new BufferedReader(new FileReader(fileName));
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();

			while (line != null){
				sb.append(line);
				sb.append(" ");
				line = br.readLine();	
			}
			everything = sb.toString();
		}

		catch (IOException e){}
		finally {
			try{
				if(br!= null) br.close();
			}
			catch (IOException | NullPointerException e) {}
		}
		ArrayList<Association> tempRelations = new ArrayList<>();
		ArrayList<Aggregation> tempAggregations = new ArrayList<>();
		ArrayList<Generalisation> tempGeneralizations = new ArrayList<>();
		Model modelForTest = parseAndProcess(everything, tempRelations, tempAggregations, tempGeneralizations);

		try{
			validate(modelForTest, tempRelations, tempAggregations, tempGeneralizations);

			Test.testPrint(modelForTest, tempRelations, tempAggregations, tempGeneralizations);
		}
		catch(MetricSyntaxException e){}

		return modelForTest;
	}

	public static Model parseAndProcess(String str, ArrayList<Association> tempRelations, ArrayList<Aggregation>tempAggregations, ArrayList<Generalisation> tempGeneralizations){
		Model pModel = new Model("");
		try{
			
			LinkedList<String> LL = new LinkedList<>();
			String[] listString = str.split("(\\s+)|(?<=,)|(?=,)|(?<=;)|(?=;)|(?<=\\))|(?=\\))|(?<=\\()|(?=\\()|(?<=:)|(?=:)");

			for (String listString1 : listString) {
				if (!listString1.equals("")) {
					LL.add(listString1);
				}
			}
			ListIterator<String> listIterator = LL.listIterator();

			String wStr = listIterator.next();
			
			try{
				if(wStr.equals("MODEL")){
					
					parseModel(LL, listIterator, pModel, tempRelations, tempAggregations, tempGeneralizations);
				}
				else
					throw new MetricSyntaxException("Error Parsing", gui);
			}
			catch(MetricSyntaxException e){}
		}
		catch(NoSuchElementException e){
			JOptionPane.showMessageDialog(gui, e.getMessage());
			
		}
		return pModel;

		
	}//end parseAndProcess


	public static void parseModel(LinkedList<String> LL, ListIterator<String> LI, Model pModel, ArrayList<Association> tempRelations, ArrayList<Aggregation> tempAggregations, ArrayList<Generalisation> tempGeneralizations) throws NoSuchElementException
	{
		try	{
			if(LI.hasNext()){
				String modelName = LI.next();
				pModel.setModelName(modelName);
			}
			else throw new MetricSyntaxException("Syntax Error", gui);

			while(LI.hasNext()){

				String type = LI.next();
				if(!(type.equals("CLASS") || type.equals("RELATION") || type.equals("GENERALIZATION") || type.equals("AGGREGATION")))
					throw new MetricSyntaxException("ERROR, EXPECTING CLASS, ASSOCIATION, GENERALIZATION OR AGGREGATION", gui);


				switch (type) {
				case "CLASS":
					parseClass(LL, LI, pModel);
					break;
				case "GENERALIZATION":
					parseGeneralization(LL, LI, tempGeneralizations);
					break;
				case "RELATION":
					parseRelation(LL, LI, pModel, tempRelations);
					break;
				case "AGGREGATION":
					parseAggregation(LL, LI, tempAggregations);
					break;
				default:
					break;	
				}
			}
		}
		catch(MetricSyntaxException ex){}
	}


	public static ListIterator<String> parseAggregation(LinkedList<String> LL, ListIterator<String> LI, ArrayList<Aggregation> tempAggregations) throws MetricSyntaxException,NoSuchElementException{
		String temp = LI.next(); //gets class name


		if(!temp.equals("CONTAINER"))
			throw new MetricSyntaxException("ERROR: EXPECTING 'CONTAINER'", gui);

		temp = LI.next();
		if(!temp.equals("CLASS"))
			throw new MetricSyntaxException("ERROR: EXPECTING 'CLASS'", gui);

		Aggregation pAggregation = new Aggregation();
		Role container = new Role();
		temp = LI.next();
		container.setClassName(temp);
		
		temp = LI.next();
		container.setClassMultiplicity(temp);

		pAggregation.setContainer(container);
		temp = LI.next(); 
		if(!temp.equals("PARTS"))
			throw new MetricSyntaxException("ERROR: EXPECTING 'PARTS'", gui);

		temp = LI.next();

		while(!temp.equals(";")){

			Role pRole = new Role();
			if(!temp.equals("CLASS"))
				throw new MetricSyntaxException("ERROR: EXPECTING 'CLASS'", gui);
			temp = LI.next();
			pRole.setClassName(temp);
			
			temp = LI.next();
			pRole.setClassMultiplicity(temp);
			
			temp = LI.next();
			pAggregation.addRole(pRole);
			if(temp.equals(","))
				temp = LI.next();
		}
		tempAggregations.add(pAggregation);
		return LI;
	}

	public static ListIterator<String> parseRelation(LinkedList<String> LL, ListIterator<String> LI, Model pModel, ArrayList<Association>tempRelations) throws MetricSyntaxException, NoSuchElementException{
		Association pRelation = new Association();

		String className = LI.next(); //gets class name

		pRelation.setRelationName(className);
		String temp = LI.next(); 
		if(!temp.equals("ROLES"))
			throw new MetricSyntaxException("ERROR: EXPECTING 'ROLES'", gui);

		temp = LI.next();

		while(!temp.equals(";")){

			Role pRole = new Role();
			if(!temp.equals("CLASS"))
				throw new MetricSyntaxException("ERROR: EXPECTING 'CLASS'", gui);
			temp = LI.next();
			pRole.setClassName(temp);

			temp = LI.next();
			pRole.setClassMultiplicity(temp);

			temp = LI.next();

			pRelation.addRole(pRole);
			if(temp.equals(","))
				temp = LI.next();
		}

		if(pRelation.getListRole().size() !=2)
			throw new MetricSyntaxException("ROLE REQUIRES EXACTLY 2 CLASSES", gui);

		tempRelations.add(pRelation); //add new relation to temporary list of relations
		return LI;
	}
	public static ListIterator<String> parseGeneralization(LinkedList<String> LL, ListIterator<String> LI, ArrayList<Generalisation> tempGeneralizations) throws MetricSyntaxException, NoSuchElementException{
		String className = LI.next(); //gets class name

		Generalisation pGeneralization = new Generalisation();
		pGeneralization.setParent(className);
		String temp = LI.next(); 
		if(!temp.equals("SUBCLASSES"))
			throw new MetricSyntaxException("ERROR: EXPECTING 'SUBCLASSES'", gui);
		temp = LI.next();

		while(!temp.equals(";")){
			pGeneralization.addChildren(temp);

			temp = LI.next();
			if(temp.equals(","))
				temp = LI.next();
		}
		tempGeneralizations.add(pGeneralization);
		return LI;

	}
	public static ListIterator<String> parseClass(LinkedList<String> LL, ListIterator<String> LI, Model pModel)throws MetricSyntaxException, NoSuchElementException{

		String className = LI.next(); //gets class name

		Classe parsedClass = new Classe(className);

		pModel.addClass(parsedClass);

		String temp = LI.next();

		if(!(temp.equals("ATTRIBUTES"))){
			throw new MetricSyntaxException("ERROR: EXPECTING 'ATTRIBUTES'", gui);
		}
		else{
			temp = LI.next();
			while(!temp.equals("OPERATIONS")){

				DataItem parsedAttribute = new DataItem();
				parsedAttribute.setName(temp);
				temp = LI.next();
				if(!temp.equals(":")) 
					throw new MetricSyntaxException("ERROR: EXPECTING ':'", gui);


				temp = LI.next();

				parsedAttribute.setType(temp);

				temp = LI.next();
				parsedClass.addAttribute(parsedAttribute);

				if(temp.equals(","))
					temp = LI.next(); 
			}
			parseOperations(LL, LI, parsedClass);

		}
		return LI;
	}


	public static ListIterator<String> parseOperations(LinkedList<String> LL, ListIterator<String> LI, Classe pClass)throws MetricSyntaxException, NoSuchElementException{
		Operation pOp;  
		String temp = LI.next();
		while(!temp.equals(";")){
			pOp = new Operation();
			pOp.setOperationName(temp);

			temp = LI.next();

			//if there's no (), then error
			if(!temp.equals("(")){
				throw new MetricSyntaxException("ERROR: EXPECTING A LIST OF PARAMETERS ENCLOSED IN ()", gui);
			}

			//Parse the parameters for the operation
			else{
				LI = parseParameters(LL, LI, pOp);
			}

			//Ensure that each operation has a : and a return type
			temp = LI.next();
			if(!temp.equals(":")) 
				throw new MetricSyntaxException("ERROR: EXPECTING A RETURN TYPE FOR THE OPERATION", gui);

			temp = LI.next();
			pOp.setOperationReturnType(temp);

			temp = LI.next();

			pClass.addOperation(pOp);

			if(temp.equals(","))
				temp = LI.next();
			//else, temp = ";" and we are done with Operations

		}
		return LI;
	}

	public static ListIterator<String> parseParameters(LinkedList<String>LL, ListIterator<String>LI, Operation pOp)throws MetricSyntaxException, NoSuchElementException{
		DataItem pDI;
		String temp = LI.next();
		while(!temp.equals(")"))
		{
			pDI = new DataItem();
			pDI.setName(temp);

			temp = LI.next();
			if(!temp.equals(":")) 				
				throw new MetricSyntaxException("ERROR: EXPECTING ':'", gui);

			temp = LI.next();
			pDI.setType(temp);

			temp = LI.next();
			pOp.addParameter(pDI);
			if(temp.equals(","))
				temp = LI.next(); 
		}
		return LI;
	}





	public static void validate(Model mod, ArrayList<Association> tempRelations, ArrayList<Aggregation> tempAggregations, ArrayList<Generalisation> tempGeneralizations) throws MetricSyntaxException, NoSuchElementException
	{
		//****************	VALIDATION FOR ASSOCIATION **********************//
		/*This code goes through all our temporary Relations (provided by the parser)
		 *It checks to ensure that all the classes involved in the relation actually exist
		 *If one of the classes doesn't exist, then we throw an exception
		 *If all the classes involved exist, then the relations are added to all the affected ModClass instances
		 */
		for (Association tempRelation : tempRelations) {
			ArrayList<Role> listOfRoles = tempRelation.getListRole();
			//test if all classes for each relation exist
			for (Role listOfRole : listOfRoles) {
				Classe classInvolvedInRelation = mod.getClassFromName(listOfRole.getClassName());
				if (classInvolvedInRelation == null) {
					throw new MetricSyntaxException("ERROR: CLASS IN RELATION DOESN'T EXIST", gui);

					/*We add a pointer to the relation from each of the affected classes in the relation */
				} else {
					classInvolvedInRelation.addAssociation(tempRelation);
				}
			}
			//System.out.println( tempRelations.get(i));
		}


		//****************	VALIDATION FOR AGGREGATIONS **********************//
		/*This code goes through all our temporary Aggregations (provided by the parser) /*
		 *It checks to ensure that all the classes involved in the relation actually exist
		 *If one of the classes doesn't exist, then we throw an exception
		 *If the classes exist, then the Aggregation is added to the container's class 
		 */
		for(int i =0; i<tempAggregations.size(); i++)
		{
			Classe container = mod.getClassFromName(tempAggregations.get(i).getContainerClass().getClassName());
			if (container == null)
				throw new MetricSyntaxException("ERROR: CLASS IN AGGREGATION DOESN'T EXIST", gui);

			ArrayList<Role> listOfRoles = tempAggregations.get(i).getListRole();


			//test if all classes for each relation exist
			for (Role listOfRole : listOfRoles) {
				Classe classInvolvedInRelation = mod.getClassFromName(listOfRole.getClassName());
				if (classInvolvedInRelation == null)
					throw new MetricSyntaxException("ERROR: CLASS IN AGGREGATION/ROLE DOESN'T EXIST", gui);
			}
			/*We add the Aggregation to the ModClass of the container */
			container.addAggregation(tempAggregations.get(i));
			//System.out.println( tempRelations.get(i));
		}

		//****************	VALIDATION FOR GENERALIZATIONS **********************//
		/*This code goes through all our temporary Generalizations (provided by the parser) /*
		 *It checks to ensure that all the classes involved in the generalization actually exist
		 *If one of the classes doesn't exist, then we throw an exception
		 *If the classes exist, then the Generalization is added to the superclass's ModClass instance 
		 */

		for(int i =0; i<tempGeneralizations.size(); i++)
		{
			Classe parent = mod.getClassFromName(tempGeneralizations.get(i).getParent());
			if(parent == null)
				throw new MetricSyntaxException("ERROR: DECLARED SUPERCLASS DOESN'T EXIST", gui);
			ArrayList<String> listOfChildren = tempGeneralizations.get(i).getChildren();
			for(int j =0; j<listOfChildren.size(); j++)
			{
				Classe childClass = mod.getClassFromName(listOfChildren.get(j));
				if(childClass == null)
					throw new MetricSyntaxException("ERROR: DELCARED SUBCLASS DOESN'T EXIST", gui);

			}
			parent.addGeneralization(tempGeneralizations.get(i));
		}
	}
}
