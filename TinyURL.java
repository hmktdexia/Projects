
public class TinyUrl {

    /**
      * This is to design shortenning URL and retrieving the orig URL by shorten URLs
      *   
      *    Considerations 1. performance - how quickly encode URL to short url and retrieve URL from short one
      *                   2. Space  - how to save large amount URLs to use relative small space
      *   
      *  There would be system design topic of tiny URL on a distributed cluster env
     **/

    Map<String, String> urlToCode;
    Map<String, String> codeToUrl;
    // Encodes a URL to a shortened URL.
    
    public TinyUrl(){
        urlToCode = new HashMap<String,String>();
        codeToUrl = new HashMap<String,String>();
    }
    public String encode(String longUrl) {
        String code=null;
        if(!urlToCode.containsKey(longUrl)){
            long count = (long)urlToCode.keySet().size();
            count++;
            code = encode(count);
            urlToCode.put(longUrl, code);
            codeToUrl.put(code, longUrl);
        }else{
            code = urlToCode.get(longUrl);
        }
        return "http://tinyurl.com/"+code;
    }

    // This encode() is to use A-Z, a-z, 0-9 to represent one character in short URL
    String encode(long num){
        StringBuffer bf = new StringBuffer();
        long n;
        while(num>0){
            n=num%62;
            if(n>=10 && n<36){
                bf.insert(0,'a'+n-10);
            }else if(n>=36){
                bf.insert(0,'A'+n-36);
            }else{
                bf.insert(0,""+n);
            }
            num=num/62;
        }
        return bf.toString();
    }
    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        shortUrl = shortUrl.trim();
        String code = (shortUrl.length()>19)? shortUrl.substring(19)
                        : shortUrl;
        
        if(codeToUrl.containsKey(code)){
            return codeToUrl.get(code);
        }
        
        return "";
    }
}
