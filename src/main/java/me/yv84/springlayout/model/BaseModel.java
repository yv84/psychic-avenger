package me.yv84.springlayout.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Id, equals, hashCode, compareTo
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable, Comparable<BaseModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public BaseModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BaseModel)) {
            return false;
        }

        BaseModel baseModel = (BaseModel) o;

        return id != null && id.equals(baseModel.id);
    }

    @Override
    public int hashCode() {
        if (id != null) {
            return id.hashCode();
        } else {
            return -1;
        }
    }

    public int compareTo(BaseModel baseModel) {
        return this.id.compareTo(baseModel.getId());
    }
}
