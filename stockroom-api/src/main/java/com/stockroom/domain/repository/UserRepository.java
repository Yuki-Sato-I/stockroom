package com.stockroom.domain.repository;

import com.stockroom.domain.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM users WHERE token=#{token} AND status = 1")
    User findByToken(String token);

    @Select("SELECT * FROM users WHERE email=#{email} AND status = 1")
    User findByEmail(String email);

    @Insert("INSERT INTO users(id, email, token, password_digest, activated, status) " +
            "VALUES(#{id,javaType=java.util.UUID,jdbcType=OTHER,typeHandler=com.stockroom.config.UuidTypeHandler}, #{email}, #{token}, #{passwordDigest}, #{activated}, #{status})")
    Boolean create(User user);

    @Update("UPDATE users " +
            "SET token=#{token} " +
            "WHERE id=#{id,javaType=java.util.UUID,jdbcType=OTHER,typeHandler=com.stockroom.config.UuidTypeHandler}")
    Boolean updateToken(User user);
}
