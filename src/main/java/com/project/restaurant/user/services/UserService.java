package com.project.restaurant.user.services;

import com.project.restaurant.dtos.PageDTO;
import com.project.restaurant.user.entities.User;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

/**
 * Represents a user service
 * @author xuanmai
 * @since 2023-11-22
 */
public interface UserService {

    /**
     * This method is used to list all users
     *
     * @return list of users
     */
    public List<User> listAll();

    /**
     * This method is used to get a user base on id
     *
     * @param id This is user id
     * @return user base on id
     */
    public Optional<User> get(long id);

    /**
     * This method is used to create a user
     *
     * @param user This is a user
     */
    public User save(User user);

    /**
     * This method is used to delete a user by id
     *
     * @param user This is a user
     */
    public void delete(User user);

    /**
     * This method is used to confirm exists Email
     *
     * @param email This is email
     * @return boolean
     */
    Boolean existsByEmail(String email);

    /**
     * This method is used to get a user base on email
     *
     * @param email This is user email
     * @return user base on email
     */
    Optional<User> getUserByEmail(String email);

    /**
     * This method is used to get a user base on id
     *
     * @param userId This is user id
     * @return user base on id
     */
    Optional<User> getUserById(Long userId);

    /**
     * This method is used to get all user
     *
     * @return user list
     */
    Page<User> getAllUsers(PageDTO pageDTO);

    /**
     * This method is used to delete a user base on id
     *
     * @param id This is userId
     */
    void deleteUserById(Long id);

    /**
     * Searches for users matching the provided search term with pagination.
     *
     * @param pageDTO     The PageDTO containing pagination information (pageNumber, pageSize).
     * @param searchTerm  The search term to filter users by (can be a name, email, or phone number).
     * @return            A Page of User entities that match the search criteria.
     */
    Page<User> searchUsersWithPagination(PageDTO pageDTO, String searchTerm);
}