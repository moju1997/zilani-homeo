package com.zhomeo.zilani.entity;

import com.jazasoft.embedded.entity.Auditable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import java.util.Objects;

@Entity
public class MedicineBrand  extends Auditable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @NotEmpty
  private String name;

  private String description;

  public MedicineBrand() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof MedicineBrand)) return false;
    MedicineBrand that = (MedicineBrand) o;
    return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, description);
  }
}
