package http.request.factory;

import http.request.path.FilePath;
import http.request.path.html.Membership;

public class MembershipPathFactory implements PathFactory {
    @Override
    public FilePath getFilePath(String[] uri) {
        return new Membership();
    }
}
