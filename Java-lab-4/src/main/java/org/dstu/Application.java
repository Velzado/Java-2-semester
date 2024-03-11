package org.dstu;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "applications")
public class Application implements CSVReadable<Application> {
  @Id
  @Column(name = "id")
  private int id;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
  }

  @Column(name = "title")
  private String title;
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }

  @Column(name = "version")
  private String version;
  public String getVersion() {
    return version;
  }
  public void setVersion(String version) {
    this.version = version;
  }

  @OneToMany(
          mappedBy = "app",
          fetch = FetchType.LAZY
  )
  private List<PhoneApplication> phones;

  public void parseCSVLine(String[] data, String[] titles) {
    for (int i = 0; i < titles.length; i++) {
      final String field = titles[i];
      final String value = data[i];
      switch (field) {
        case "id" -> this.id = Integer.parseInt(value);
        case "title" -> this.title = value;
        case "version" -> this.version = value;
      }
    }
  }

  public Application() {
    id = 0;
    title = "";
    version = "";
  }

  public Application(int id) {
    this.id = id;
    title = "";
    version = "";
  }

  public Application create() {
    return new Application();
  }

  @Override
  public String toString() {
    return String.format("%3d%15s%20s", id, title, version);
  }
}
