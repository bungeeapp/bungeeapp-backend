package com.bungeeinc.bungeeapp.database.dao;

import com.bungeeinc.bungeeapp.database.models.BungeeProfile;
import org.jdbi.v3.sqlobject.config.RegisterColumnMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.Optional;

public interface IProfileDao {

    @SqlUpdate("insert into profiles (user_id, nickname, biography, email, banner_key, avatar_uuid, birthday)" +
            " values  (:userId, :nickname, :biography," +
            " :email, :bannerKey, :avatarUUID, :birthday)")
    @GetGeneratedKeys
    int createProfile(@BindBean BungeeProfile profile);

    @SqlQuery("select * from profiles where user_id = ?")
    @RegisterColumnMapper(BungeeProfile.Mapper.class)
    BungeeProfile getByUserId(int id);

    @SqlUpdate("update profiles set nickname = :nickname, biography = :biography, private = :private, email = :email," +
            " banner_key = :bannerKey, avatar_uuid = :avatarUUID, birthday = :birthday " +
            "where user_id = :userId")
    void updateProfile(@BindBean BungeeProfile profile);

    @SqlQuery("select count(*) from profiles where user_id = ?")
    boolean isExistById(Optional<Integer> id);

}
