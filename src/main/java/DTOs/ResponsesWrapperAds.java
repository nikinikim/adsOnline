package DTOs;

import java.util.List;

public class ResponsesWrapperAds {

    private int count;
    private List<AdsDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<AdsDTO> getResults() {
        return results;
    }

    public void setResults(List<AdsDTO> results) {
        this.results = results;
    }
}
