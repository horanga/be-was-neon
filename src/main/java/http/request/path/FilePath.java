package http.request.path;

public interface FilePath {
    String relativePath = "src/main/resources/static/";

    default java.io.File getFile() {
        return new java.io.File(relativePath, getResourcePath());
        //FilePath를 구현한 클래스에서 각각 파일의 나머지 주소를 반환한다.
    }

    public String getResourcePath();

}
