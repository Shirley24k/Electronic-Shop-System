package domain;

import java.util.ArrayList;

public interface IFileWriter<T>{
    public void writeFile(String fileName, ArrayList<T> list);
}
