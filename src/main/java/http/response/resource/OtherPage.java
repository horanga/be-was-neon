package http.response.resource;

import exception.NonexistentFileException;

import static http.response.Path.RELATIVE_PATH;

public class OtherPage implements File {
    @Override
    public java.io.File getFile(String[] resourcePath) {
        String path = joinPath(resourcePath);
        java.io.File file = new java.io.File(RELATIVE_PATH, path);
        checkFileExist(file);
        return file;
    }

    private void checkFileExist(java.io.File file) {
        if (!file.exists()) {
            throw new NonexistentFileException("파일이 존재하지 않습니다.");
        }
    }
}
