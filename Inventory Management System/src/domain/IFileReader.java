package domain;

import java.util.ArrayList;

public interface IFileReader<T> {
    public ArrayList<T> readFile(String filename);
}
