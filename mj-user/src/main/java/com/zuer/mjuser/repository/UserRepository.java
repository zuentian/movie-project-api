package com.zuer.mjuser.repository;

import com.zuer.mjuser.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository  extends JpaRepository<User,String> {

    /**
     *  and
     * @param id
     * @param name
     * @return
     */
    User findByIdAndName(String id, String name);


    User findByNameAndPassword(String name, String password);

    /**
     *  or
     * @param id
     * @param name
     * @return
     */
    User findByIdOrName(String id, String name);


    /**
     * between
     * @param start
     * @param end
     * @return
     */
    List<User> findByCreateDateBetween(Date start, Date end);


    /**
     * lessThan
     * @param start
     * @return
     */
    List<User> getByCreateDateLessThan(Date start);

    /**
     * Greater Than
     * @param start
     * @return
     */
    List<User> findByCreateDateGreaterThan(Date start);


    /**
     * is null
     * @return
     */
    List<User> findByNameIsNull();


    /**
     * in
     * @param nameList
     * @return
     */
    List<User> findByNameIn(Collection<String> nameList);
}
