//ALLOO

package met;

import java.util.ArrayList;

public class Test {

	
	public static void testPrint(Model mod, ArrayList<Association> tempRelations, ArrayList<Aggregation> tempAggregations, ArrayList<Generalisation> tempGeneralizations) throws MetricSyntaxException
	{
		System.out.println("********************BEGIN TEST********************");
		System.out.println("Model Name: " + mod.getModelName());
		ArrayList<Classe> classList = mod.getListClass();
		
		/////////// 		PRINT TEST -- ATTRIBUTES AND OPERATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAttribute and listOperations arrays /*
		 */
		for(int i=0; i<classList.size(); i++)
		{
			System.out.println("Class Name: " + classList.get(i).getClassName());
			for(int j =0; j<classList.get(i).getListAttribute().size(); j++)
			{
				System.out.println("\tAttribute #" + j + classList.get(i).getListAttribute().get(j));
			} 

			for(int j =0; j<classList.get(i).getListOperation().size(); j++)
			{
				System.out.println("\tOperation #" + j + "\t" +classList.get(i).getListOperation().get(j));
			}
			System.out.println("******************");
		}
		
		
                /////////// 		PRINT TEST -- RELATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAggregation attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<mod.getListClass().size(); i++)
		{
			for(int j=0; j<mod.getListClass().get(i).getListAssociation().size(); j++)
			{
				System.out.println("CLASS NAME: " + mod.getListClass().get(i).getClassName() + "\t" + mod.getListClass().get(i).getListAssociation().get(j));
			}
		}
		
		
		
		/////////// 		PRINT TEST -- AGGREGATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listAggregation attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<mod.getListClass().size(); i++)
		{
			for(int j=0; j<mod.getListClass().get(i).getListAggregation().size(); j++)
			{
				System.out.println(mod.getListClass().get(i).getListAggregation().get(j));
			}
		}
		
		/////////// 		PRINT TEST -- GENERALIZATIONS 				///////////////
		/*To test, we go through all the classes, and print out their listGeneralization attribute /*
		 * It should only appear for the host class(the one specified as the Container
		 */
		for(int i =0; i<mod.getListClass().size(); i++)
		{
			for(int j=0; j<mod.getListClass().get(i).getListGeneralization().size(); j++)
			{
				System.out.println(mod.getListClass().get(i).getListGeneralization().get(j));
			}
		}	
	}
}
