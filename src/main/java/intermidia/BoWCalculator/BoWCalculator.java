package intermidia.BoWCalculator;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class BoWCalculator 
{
    public static void main( String[] args ) throws Exception
    {
    	DataSource dataSource = new DataSource(args[0]);    	
    	Instances instances = dataSource.getDataSet();
    	/*System.out.println("Shot \t Text");*/    	
    	Instance inst = instances.firstInstance();
    	/*for(Instance attribute : inst.dataset())
    	{
    		System.out.println(attribute);
    	}*/
       	
    	StringToWordVector bowFilter = new StringToWordVector(500);
    	bowFilter.setInputFormat(inst.dataset());
    	Instances output = Filter.useFilter(inst.dataset(), bowFilter);
    	System.out.println(output);
    	/*Instance bow = bowFilter.output();*/
    	/*System.out.println(bow);*/

    }
}
