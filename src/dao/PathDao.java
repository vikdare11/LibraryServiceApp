package dao;

import domain.Path;

import java.util.List;

public interface PathDao {
    int create(Path path);
    Path read(int idPath);
    void update(Path path);
    void delete(Path path);
    List<Path> getPathsList();
}
