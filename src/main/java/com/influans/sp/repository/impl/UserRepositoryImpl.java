package com.influans.sp.repository.impl;

import com.influans.sp.entity.EntityId;
import com.influans.sp.entity.UserEntity;
import com.influans.sp.entity.def.EntityIdDef;
import com.influans.sp.repository.custom.UserRepositoryCustom;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * @author hazem
 */
public class UserRepositoryImpl extends GenericRepositoryImpl<UserEntity, EntityId> implements UserRepositoryCustom {
    @Override
    public Class<UserEntity> getTClass() {
        return UserEntity.class;
    }

    @Override
    public EntityId getId(UserEntity userEntity) {
        return userEntity.getUserId();
    }

    @Override
    public List<UserEntity> findUsersBySessionId(String sessionId) {
        final Query q = new Query();
        q.addCriteria(Criteria.where("_id.sId").is(sessionId));
        return mongoTemplate.find(q, getTClass());
    }

    @Override
    public UserEntity findUser(String sessionId, String username) {
        final Query q = new Query();
        q.addCriteria(Criteria.where("_id." + EntityIdDef.SESSION_ID).is(sessionId)
                .andOperator(Criteria.where("_id." + EntityIdDef.ENTITY_ID).is(username)));
        return mongoTemplate.findOne(q, getTClass());
    }
}
