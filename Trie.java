
package com.code.algorithm.blocks;

/**
  * Trie is often used to store words like dictionary and it is highly efficient for adding, retrieving & removing words
  *      like a tree node with array of nodes, a TrieNode has normally 26 sub nodes;
  *
  * The algorithms on Trie are usually related to language words retrieving or manipulations.
  *
**/
public class Trie {

    class TrieNode {
		
	      char val ;	
	      String word;
	      TrieNode [] edges;
	
	      public TrieNode(){
		      edges = new TrieNode[26];
	      }
	      public TrieNode(char ch){
	            val = ch;
	            edges = new TrieNode[26];
	      }
	      String search(String word){
	            TrieNode root = this;
	            char ch;
	            for(int i=0;i<word.length();i++){
	                ch=word.charAt(i);
	                if(root.word != null
	                    || root.edges[ch-'a'] == null)
	                    break;
	                root = root.edges[ch-'a'];
	            }
	            return root.word;
	        }
	
	      public String getWord(){
		        return word;
	      }
    }
    private TrieNode root;
    
    public Trie() {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) {
    	TrieNode p = root,p1;
    	for(int i=0; i<word.length(); i++){    		
    		p1 =p.edges[index(word.charAt(i))] ;    		
    		if(p1 == null){
    			p1 = new TrieNode();
    			p1.val = word.charAt(i);
    			p.edges[index(word.charAt(i))]=p1;
    		}
    		p = p1;    		
    	} 
    	p.word = word;
        
    }

    // Returns if the word is in the trie.
    public boolean search(String word) {
    	TrieNode p = root;
    	for(int i=0; i<word.length(); i++){
    		if(p.edges[index(word.charAt(i))] == null)
    			return false;
    		p = p.edges[index(word.charAt(i))];
    	}
    
    	return (p.word != null && p.word.equals(word) )? true : false;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) {
    	TrieNode p = root;
    	for(int i=0; i<prefix.length(); i++){
    		if(p.edges[index(prefix.charAt(i))] == null)
    			return false;
    		p = p.edges[index(prefix.charAt(i))];
    	}
    	
    	return true;    
    }

    int index(char a){
    	return a -'a'; 
    }
    /**
     * This replaceWords is an algorithm example to use Trie.
     *     replace word in sentence with prefix in dictionary
     *   	Input: dict = ["cat", "bat", "rat"]
     *		sentence = "the cattle was rattled by the battery"
     *         Output: "the cat was rat by the bat"
     **/
    public String replaceWords(List<String> dict, String sentence) {
	        
	        if(dict == null || dict.size()==0)
	            return sentence;
	        
	        TrieNode trie = new TrieNode();
	        TrieNode root;
	        char ch;
	        for(String w : dict){
	            root = trie;
	            for(int i=0;i<w.length();i++){
	                ch=w.charAt(i);
	                if(root.edges[ch-'a'] == null){
	                    root.edges[ch-'a'] = new TrieNode(ch);
	                }
	                root = root.edges[ch-'a'];
	            }
	            root.word=w;
	        }
	        String[] words = sentence.split(" ");
	        
	        Map<String,String> map = new HashMap<String, String>();
	        String w;
	        for(int i=0;i<words.length;i++){
	            root = trie;
	            if(map.containsKey(words[i])){
	                w = map.get(words[i]);
	                words[i]=w;
	            }else{
	                w = root.search(words[i]);
	                if(w != null){
	                    map.put(words[i], w);
	                    words[i]=w;
	                } 
	            }
	        }
	        StringBuilder builder = new StringBuilder();
	        for(String word : words){
	            builder.append(word+" ");
	        }
	        
	        return builder.toString().trim();
	    }
    
    public static void main(String[] args){
    	
    	Trie trie = new Trie();
    	
    	trie.insert("a");
    	System.out.println(trie.search("a"));
	    
	String sentence="the cattle was rattled by the battery";
	String[] words={"cat", "bat", "rat"};
	List<String> dict = Arrays.asList(words);
	new Trie().replaceWords(dict, sentence);
	System.out.println(sentence);
    }
}
