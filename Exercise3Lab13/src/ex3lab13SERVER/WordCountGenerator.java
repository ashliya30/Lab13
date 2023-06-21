package ex3lab13SERVER;

public class WordCountGenerator {

	/**
	 * This method generates text length.
	 * 
	 * @return current date
	 */
	public int wordsCount(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }
        String[] words = text.trim().split("\\s+");
        return words.length;
	}
}
