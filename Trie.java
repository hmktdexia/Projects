
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
    
    public static void main(String[] args){
    	
    	Trie trie = new Trie();
    	
    	trie.insert("a");
    	System.out.println(trie.search("a"));
    }
}
