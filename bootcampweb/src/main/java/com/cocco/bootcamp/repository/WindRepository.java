package com.cocco.bootcamp.repository;

import com.cocco.bootcamp.domain.Wind;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by santi on 30/1/2017.
 */
public interface WindRepository extends CrudRepository<Wind, Integer> {
}
