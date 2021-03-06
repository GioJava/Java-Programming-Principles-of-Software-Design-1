 import java.util.*;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MarkovOne {
    private String myText;
    private Random myRandom;
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setRandom(int seed){
        myRandom = new Random(seed);
    }
    
    public void setTraining(String s){
        myText = s.trim();
    }
    
    public String getRandomText(int numChars){
        if (myText == null){
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-1);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        
        for(int k=0; k < numChars; k++){
            ArrayList<String> follows = getFollows(key);
            if(follows.size()==0){
                break;
            }
            
            index = myRandom.nextInt(follows.size());
            String successor = follows.get(index);
            sb.append(successor);
            key = successor;
        }
        
        return sb.toString();
    }
    
    public ArrayList<String> getFollows(String key){
        ArrayList<String> follows = new ArrayList<String>();
        int pos =0;
        while(true){
            int index = myText.indexOf(key,pos);
            int indexOfSuccessor = index + key.length();
            
            if(index == -1 || indexOfSuccessor >= myText.length()){
                break;
            }
            
            String successor = myText.substring(indexOfSuccessor,indexOfSuccessor+1);
            follows.add(successor);
            // key is one character length
            pos = index+1;
        }
        return follows;
    }
}
