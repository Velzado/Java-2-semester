package org.dstu.domain;

import java.io.Serializable;

public interface IUniversityPerson extends Serializable {
    default void print() {
        System.out.println(toString());
    }
}
