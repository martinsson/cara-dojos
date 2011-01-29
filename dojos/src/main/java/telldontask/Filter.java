package telldontask;

import java.util.ArrayList;
import java.util.List;

public interface Filter {
    List<Keyword> filter(List<Keyword> keywordList);
    
    public static class NoFilter implements Filter {
        public List<Keyword> filter(List<Keyword> keywordList) {
            return keywordList;
        }
    }

    public static class AlreadyPublishedKeywordsRemover implements Filter {
        public List<Keyword> filter(List<Keyword> keywordList) {
            List<Keyword> remainingKeywords = new ArrayList<Keyword>();
            for (Keyword keyword : keywordList) {
                if ("no".equals(keyword.isPublished()))
                    remainingKeywords.add(keyword);
            }
//            List<Keyword> remainingKeywords = select(keywordList, having(on(Keyword.class).isPublished(), equalTo("no")));
            return remainingKeywords;
        }
    }
}