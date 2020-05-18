package br.com.hrs.core.model;

import java.io.Serializable;

public interface EntityKey<ID> extends Serializable {
    ID getId();
}
