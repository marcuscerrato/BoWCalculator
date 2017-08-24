package intermidia.BoWCalculator;

import java.util.Enumeration;

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
    	Instances input = dataSource.getDataSet(0);
    	
/*    	System.out.println(instances.size());
    	for(Instance instance : instances)
    	{
    		System.out.println(instance.value(0) + " - " + instance.stringValue(1));
    	}
*/    	
    	
    	
       	
    	StringToWordVector bowFilter = new StringToWordVector(500);
    	bowFilter.setInputFormat(input);
    	bowFilter.setOutputWordCounts(true);
    	bowFilter.setWordsToKeep(10);
    	Instances output = Filter.useFilter(input, bowFilter);
    	System.out.println(output);
/*    	for(Instance instance : output)
    	{    		
    		System.out.println(instance.value(0) + " - " + instance.stringValue(1));
    	}*/
    	/*Instance bow = bowFilter.output();*/
    	/*System.out.println(bow);*/

    }
}
