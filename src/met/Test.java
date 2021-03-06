
package met;

import java.util.ArrayList;

public class Test {

	
	public static void testPrint(Model mod, ArrayList<Association> tempRelations, ArrayList<Aggregation> tempAggregations, ArrayList<Generalisation> tempGeneralizations) throws MetricSyntaxException
	{
		System.out.println("********************BEGIN TEST********************");
		System.out.println("Model Name: " + mod.getModelName());
		ArrayList<Classe> classList = Model.getListClass();
		
		/////////// 		PRINT TEST -- ATTRIBUTES AND OPERATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAttribute and listOperations arrays /*
		 */
		for(int i=0; i<classList.size(); i++)
		{
			System.out.println("Class Name: " + classList.get(i).getClassName());
			
			System.out.println("--------------------------------------------------");
			
	//		System.out.println("\tANA(" + classList.get(i).getClassName() + ") = "+ classList.get(i).ANA());
	//		System.out.println("\tNOM(" + classList.get(i).getClassName() + ") = "+ classList.get(i).NOM());
	//		System.out.println("\tNOA(" + classList.get(i).getClassName() + ") = "+ classList.get(i).NOA());
	//		System.out.println("\tITC(" + classList.get(i).getClassName() + ") = "+ classList.get(i).ITC());
	//		System.out.println("\tETC(" + classList.get(i).getClassName() + ") = "+ classList.get(i).ETC());
	//		System.out.println("\tCAC(" + classList.get(i).getClassName() + ") = "+ classList.get(i).CAC());
	//		System.out.println("\tDIT(" + classList.get(i).getClassName() + ") = "+ classList.get(i).DIT());
	//		System.out.println("\tCLD(" + classList.get(i).getClassName() + ") = "+ classList.get(i).CLD());
			
			System.out.println("--------------------------------------------------");
			
			//System.out.println("ClassParent : " + Generalisation.getParent());
			if (classList.get(i).getListGeneralization().size()>0){
			System.out.println("SubClasses : " + classList.get(i).getListGeneralization().get(0).getChildren().get(0));} //Generalisation.getChildren());
			//Generalisation.
			
			System.out.println("--------------------------------------------------");
			
		//	System.out.println("\tNOC(" + classList.get(i).getClassName() + ") = "+ classList.get(i).NOC());
		//	System.out.println("\tNOD(" + classList.get(i).getClassName() + ") = "+ classList.get(i).NOD());
			
			System.out.println("--------------------------------------------------");
			
			for(int j =0; j<classList.get(i).getListAttribute().size(); j++)
			{
				System.out.println("\tAttribute #" + (j+1) + classList.get(i).getListAttribute().get(j));
			} 
			
			System.out.println("--------------------------------------------------");

			for(int j =0; j<classList.get(i).getListOperation().size(); j++)
			{
				System.out.println("\tOperation #" + (j+1) + "\t" +classList.get(i).getListOperation().get(j));
			}
			
			System.out.println("\n************************************");
		}
		
		
        /////////// 		PRINT TEST -- RELATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAggregation attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<Model.getListClass().size(); i++)
		{
			for(int j=0; j<Model.getListClass().get(i).getListAssociation().size(); j++)
			{
				System.out.println("CLASS NAME: " + Model.getListClass().get(i).getClassName() + "\t" + Model.getListClass().get(i).getListAssociation().get(j));
			}
		}
		
		
		
		/////////// 		PRINT TEST -- AGGREGATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAggregation attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<Model.getListClass().size(); i++)
		{
			for(int j=0; j<Model.getListClass().get(i).getListAggregation().size(); j++)
			{
				System.out.println(Model.getListClass().get(i).getListAggregation().get(j));
			}
		}
		
		/////////// 		PRINT TEST -- GENERALIZATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listGeneralization attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<Model.getListClass().size(); i++)
		{
			for(int j=0; j<Model.getListClass().get(i).getListGeneralization().size(); j++)
			{
				System.out.println(Model.getListClass().get(i).getListGeneralization().get(j));
			}
		}
		
	}
}
