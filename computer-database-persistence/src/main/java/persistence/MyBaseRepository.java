package persistence;

import java.io.Serializable;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
interface MyBaseRepository<T, S extends Serializable> extends Repository<T, S> {

  T findOne(S id);

  T save(T entity);
}

