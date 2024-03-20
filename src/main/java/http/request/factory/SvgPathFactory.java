package http.request.factory;

import http.request.path.FilePath;
import http.request.path.svg.*;

import java.util.Arrays;

public class SvgPathFactory implements PathFactory {
    @Override
    public FilePath getFilePath(String[] uri) {
        return SvgType.chooseSvgType(uri);
    }

    enum SvgType {
        BOOKMARK("/img/bookMark.svg", new BookMark()),
        CHEVRON_LEFT("/img/ci_chevron-left.svg", new ChevronLeft()),
        CHEVRON_RIGHT("/img/ci_chevron-right.svg", new ChevronRight()),
        LIKE("/img/like.svg", new Like()),
        SENDLINK("/img/sendLink.svg", new SendLink()),
        SIGNITURE("/img/signiture.svg", new SignitureSvg());

        String uri;
        FilePath filePath;


        SvgType(String uri, FilePath filePath) {
            this.uri = uri;
            this.filePath = filePath;
        }

        public static FilePath chooseSvgType(String[] uri) {
            return Arrays.stream(SvgType.values()).filter(svgType ->
                            Arrays.asList(uri).contains(svgType.uri)).findFirst().
                    map(svgType -> svgType.filePath).get();
        }
    }
}
