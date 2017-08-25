package intermidia.BoWCalculator;

import java.io.FileWriter;
import java.io.PrintWriter;

import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stemmers.SnowballStemmer;
import weka.core.stopwords.Rainbow;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.StringToWordVector;

public class BoWCalculator 
{
    public static void main( String[] args ) throws Exception
    {
    	DataSource dataSource = new DataSource(args[0]);
    	int dictionarySize = Integer.parseInt(args[1]);
    	Instances input = dataSource.getDataSet(0);    	    	    	

    	//Setting the BoW parameters
    	StringToWordVector bowFilter = new StringToWordVector(dictionarySize);
    	bowFilter.setOutputWordCounts(true);
    	bowFilter.setLowerCaseTokens(true);
    	//Uses Rainbow stop words
    	bowFilter.setStopwordsHandler(new Rainbow());    	
    	//Uses Porter stemmer 
    	bowFilter.setStemmer(new SnowballStemmer());
    	
    	bowFilter.setInputFormat(input);
    	Instances output = Filter.useFilter(input, bowFilter);

    	
    	//Update dictionary size because Weka BoW doesn't respect the desired dictionary size, 
    	//outputting an approximate value.
    	dictionarySize = output.firstInstance().numAttributes() - 1;
    	
    	//Accumulates the words histograms for each shot
    	int outputBuffer[][] = new int[input.size()][dictionarySize];  
    	int lastShot = 1;
    	for(Instance instance : output)
    	{    		
    		for(int i = 0; i < dictionarySize; i++)
    		{
    			outputBuffer[((int)instance.value(0)) - 1][i] += instance.value(i + 1);
    			lastShot = (int)instance.value(0);
    		}
    	}
    	
    	
    	FileWriter bowWriter = new FileWriter(args[2]);
    	for(int i = 0; i < lastShot; i++)
    	{
    		bowWriter.write(Integer.toString(i + 1));
    		for(int j = 0; j < outputBuffer[i].length; j++)
    		{    			
    			bowWriter.write(" " + outputBuffer[i][j]);
    		}
    		bowWriter.write("\n");
    	}
    	bowWriter.close();

    	//Prints dictionary file on fourth argument.
    	try(PrintWriter dictionaryOutput = new PrintWriter(args[3])  )
    	{
    	    dictionaryOutput.println(output);
    	}
    	
    }
}

