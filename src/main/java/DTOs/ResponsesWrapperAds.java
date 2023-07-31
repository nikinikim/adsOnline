package DTOs;

import java.util.List;

public class ResponsesWrapperAds {

    private int count;
    private List<Ads> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Ads> getResults() {
        return results;
    }

    public void setResults(List<Ads> results) {
        this.results = results;
    }
}
