package intermidia.BoWCalculator;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class BoWCalculator 
{
    public static void main( String[] args ) throws Exception
    {
    	//StringToWordVector bowFilter = new StringToWordVector(500);
    	DataSource dataSource = new DataSource(args[0]);    	
    	Instances instances = dataSource.getDataSet();
    	System.out.println("Shot \t Text");
    	int i = 0;
    	for(Instance inst : instances)
    	{
    		i++;
        	System.out.println(inst.attribute(1));
    	}
    	System.out.println("Inst qty: " + i);
    }
}
