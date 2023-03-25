package model;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class TodoExtractor {

	public boolean cutInput(File input, File output, String[] searchWords) {
		
		// Store unique lines
        Set<String> uniqueLines = new HashSet<String>();
	
        if(input.isFile()) {
	        try {
	            File modifiedOutput = new File(output.getAbsoluteFile() + File.separator + "autoTODO" + " - " + input.getName() + ".txt");
	            boolean isNewFile = false;
	            if(modifiedOutput.createNewFile()) {
	            	isNewFile = true; 
	            }
	            // Reader and Writer
	            FileReader inputReader = new FileReader(input);
	            BufferedReader bufferedInputReader = new BufferedReader(inputReader);
	            FileReader outputReader = new FileReader(modifiedOutput);
	            BufferedReader bufferedOutputReader = new BufferedReader(outputReader);
	            FileWriter outputWriter = new FileWriter(modifiedOutput, true);
	            
				String line;
				// Saves already existent lines if available
				if(!isNewFile) {
					while ((line = bufferedOutputReader.readLine()) != null) {
					 	uniqueLines.add(line);
					}
				}
				while ((line = bufferedInputReader.readLine()) != null) {
					for (String searchWord : searchWords) {
					       int index = line.indexOf(searchWord);
					       if (index >= 0) {
					           // Write the part of the line after the search word to the output file
					    	   String extractedLine = line.substring(index);
					    	   // Add the extracted line to the set if it's not already in the set
					    	   if (uniqueLines.add(extractedLine)) {
					    		   // Write the extracted line to the output file
					    		   outputWriter.write(extractedLine + "\n");
				               }
				            }
		     		}
			     }
	        bufferedInputReader.close();
	        inputReader.close();
	        bufferedOutputReader.close();
	        outputReader.close();
	        outputWriter.close();
	        return true;
	        } catch (IOException e) {
	            return false;
	        }
        }else {
        	return false;
        }
	}	
}
