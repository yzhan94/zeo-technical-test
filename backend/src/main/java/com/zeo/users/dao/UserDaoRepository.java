package com.zeo.users.dao;

import com.zeo.users.ZeoUsersApplication;
import com.zeo.users.model.UserDao;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@ConditionalOnProperty(value = "persistence.mode", havingValue = ZeoUsersApplication.PERSISTENCE_PERSISTENT)
public interface UserDaoRepository extends ListCrudRepository<UserDao, String> {
    Optional<UserDao> findByEmail(final String email);
}
