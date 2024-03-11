package com.tom.jpatest.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.tom.jpatest.entity.SoftDeleteEntity;

/**
 * The Spring Data JPA Framework soft delete repository.
 * 
 * @link https://stackoverflow.com/questions/19323557/handling-soft-deletes-with-spring-jpa
 * 		 https://stackoverflow.com/questions/58289970/jpa-soft-delete-repository-auditing
 * @param <T> - the domain type the repository manages
 * @param <ID> - the type of the id of the entity the repository manages
 */
@NoRepositoryBean
public interface SoftDeleteRepository<T extends SoftDeleteEntity<ID, T>, ID extends Serializable> 
extends JpaRepository<T, ID>, CrudRepository<T, ID> {

	/** Override JpaRepository */
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.isDeleted = false")
	List<T> findAll();

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.isDeleted = false")
	List<T> findAll(Sort sort);
	
	// TODO
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e ")
	List<T> findAllWithDeleted();
	
	// TODO
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e ")
	List<T> findAllWithDeleted(Sort sort);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id in ?1 and e.isDeleted = false")
	List<T> findAllById(Iterable<ID> ids);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id in ?1")
	List<T> findAllByIdWithDeleted(Iterable<ID> ids);
	
	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.isDeleted = false")
	Page<T> findAll(Pageable pageable);
	
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e")
	Page<T> findAllWithDeleted(Pageable pageable);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
	T getOne(ID id);
	
	/** override CrudRepository */
//	@Override
//	@Transactional(readOnly = true)
//	@Query("SELECT e FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
//	Optional<T> findById(ID id);
//	
//	@Override
//	@Transactional(readOnly = true)
//	@Query("SELECT count(e)>0 FROM #{#entityName} e WHERE e.id = ?1 and e.isDeleted = false")
//	boolean existsById(ID id);

	@Override
	@Transactional(readOnly = true)
	@Query("SELECT count(0) FROM #{#entityName} e WHERE e.isDeleted = false")
	long count();
	
	@Override
	@Transactional
	default void delete(T entity) {
		entity.setDeleted(true);
		this.save(entity);
	}

	@Override
	@Transactional
	default void deleteAll(Iterable<? extends T> entities) {
		Assert.notNull(entities, "Entities must not be null!");
		for (T entity : entities) {
			if (entity != null) {
				entity.setDeleted(true);
				this.delete(entity);
			}
		}
	}
	
	@Override
	@Transactional
	default void deleteAllInBatch() {
		this.deleteAll();
	}
	
	@Override
	@Transactional
	default void deleteAllInBatch(Iterable<T> entities) {
		this.deleteAll(entities);
	}
	
	@Override
	@Transactional
	default void deleteById(ID id) {
		Optional<T> o = findById(id);
		if (o.isPresent()) {
			T t = o.get();
			t.setDeleted(true);
			save(t);
		}
	}
	
	@Override
	@Transactional
	default void deleteAllByIdInBatch(Iterable<ID> ids) {
		List<T> all = findAllById(ids);
		this.deleteAll(all);
	}
	
	@Override
	@Transactional
	default void deleteAllById(Iterable<? extends ID> ids) {
		Assert.notNull(ids, "Ids must not be null!");
		for (ID id : ids) 
			this.deleteById(id);
	}
	
	@Override
	@Transactional
	default void deleteAll() {
		List<T> entities = this.findAll();
		this.deleteAll(entities);
	}
	
}
