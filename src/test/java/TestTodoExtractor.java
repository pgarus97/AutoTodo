import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import model.TodoExtractor;

public class TestTodoExtractor {

	private TodoExtractor todoExtractor = new TodoExtractor();
	
	@Test
	public void testExtractingSuccess() {
		File input = new File("src/test/resources/testInput.txt");
		File output = new File("src/test/resources/");
		String[] searchWords = new String[1];
		searchWords[0] = "to do";
		assertTrue(todoExtractor.cutInput(input, output, searchWords));
		new File(output.getAbsolutePath()+ "/autoTODO - testInput.txt.txt").delete();
	}
	
	@Test
	public void testExtractingInputPathFailure() {
		File input = new File("src/test/resources/");
		File output = new File("src/test/resources/");
		String[] searchWords = new String[1];
		searchWords[0] = "to do";
		assertFalse(todoExtractor.cutInput(input, output, searchWords));
	}

}
