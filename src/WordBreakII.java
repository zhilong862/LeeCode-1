import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        if(wordDict.isEmpty() || s.isEmpty()){
            return new ArrayList<>();
        }
        int maxLength = 0;
        Set<String> wordSet = new HashSet<>();
        for(String word : wordDict){
            maxLength = Integer.max(maxLength, word.length());
            wordSet.add(word);
        }
        if(!canForm(s, wordSet)){
            return new ArrayList<>();
        }
        ArrayList<String>[] table = new ArrayList[s.length() + 1];
        table[0] = new ArrayList<>();
        table[0].add("");
        for(int i = 0; i < table.length; i++){
            if(table[i] == null){
                continue;
            }
            for(int j = i; j < table.length; j++){
                String word = s.substring(i, j);
                if(wordSet.contains(word)){
                    if(table[j] == null){
                        table[j] = new ArrayList<>();
                    }
                    for(String preWord: table[i]){
                        if(!preWord.equals("")){
                            table[j].add(preWord + " " + word);    
                        }else{
                            table[j].add(word);
                        }
                    }
                }
            }
        }
        return table[s.length()];
    }
    
    private boolean canForm(String s, Set<String> wordSet){
        boolean[] canFormS = new boolean[s.length() + 1];
        canFormS[0] = true;
        for(int i = 1; i < canFormS.length; i++){
            for(int j = i; j < canFormS.length; j++){
                if(canFormS[i - 1] && wordSet.contains(s.substring(i - 1, j))){
                    canFormS[j] = true;
                }
            }
        }
        return canFormS[s.length()];
    }
}
