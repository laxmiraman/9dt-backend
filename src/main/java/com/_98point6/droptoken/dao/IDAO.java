package com._98point6.droptoken.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

public interface IDAO<T> {

	public List<T> loadAll();

	public String save(T domain);

	public void update(T domain);

	public void delete(T domain);

}
