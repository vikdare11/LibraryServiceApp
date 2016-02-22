package dao;

import domain.Path;

import java.util.List;

/**
 * Created by Vika on 2/22/2016.
 */
public interface PathDao {
    int create(Path path);
    Path read(int idPath);
    void update(Path path);
    void delete(Path path);
    List<Path> getPathsList();
}
