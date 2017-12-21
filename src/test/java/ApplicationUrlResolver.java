public class ApplicationUrlResolver {
    private final String baseUrl;

    public ApplicationUrlResolver(String baseUrl) {
        if (baseUrl.endsWith("/")) {
            this.baseUrl = baseUrl.substring(0,baseUrl.length()-1);
        } else {
            this.baseUrl = baseUrl;
        }
    }

    public String resolve(String relativeUrl) {
        return baseUrl + relativeUrl;
    }
}
