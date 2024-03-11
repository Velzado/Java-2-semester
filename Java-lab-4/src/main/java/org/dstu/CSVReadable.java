package org.dstu;

public interface CSVReadable<T> {

  void parseCSVLine(String[] data, String[] titles);

  T create();
}
