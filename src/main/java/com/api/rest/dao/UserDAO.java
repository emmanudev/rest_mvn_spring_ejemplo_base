package com.api.rest.dao;

import java.util.List;

import com.api.rest.entity.User;

public interface UserDAO {

	public List<User> findAll();

    public User findById(int id_user);

    public void save(User user);

    public void deleteById(int id_user);
}
