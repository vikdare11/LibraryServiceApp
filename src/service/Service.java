package service;

import java.io.Serializable;

public interface Service<K, T extends Serializable> {
    T execute(K bean);
}
