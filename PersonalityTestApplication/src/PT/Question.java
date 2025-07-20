package PT;
public class Question {
    private String text, optionA, optionB, optionC, optionD;
    public Question(String text, String a, String b, String c, String d) {
        this.text = text;
        this.optionA = a;
        this.optionB = b;
        this.optionC = c;
        this.optionD = d;
    }
    public String getText() {
    	return text;
    	}
    public String getOptionA() {
    	return optionA; 
    	}
    public String getOptionB() {
    	return optionB; 
    	}
    public String getOptionC() {
    	return optionC; 
    	}
    public String getOptionD() { 
    	return optionD;
    	}
}

