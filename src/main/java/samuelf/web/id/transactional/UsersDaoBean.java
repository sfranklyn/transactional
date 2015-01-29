/*
 * Copyright 2013 Samuel Franklyn <sfranklyn@gmail.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package samuelf.web.id.transactional;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import org.apache.deltaspike.jpa.api.transaction.Transactional;

/**
 *
 * @author Samuel Franklyn <sfranklyn@gmail.com>
 */
@Singleton
@Transactional
public class UsersDaoBean {

    @Inject
    private EntityManager em;

    public Users selectByUserName(String userName) {
        Query query = em.createNamedQuery("Users.selectByUserName");
        query.setParameter("userName", userName);
        Users users = null;
        try {
            users = (Users) query.getSingleResult();
        } catch (NoResultException ex) {
        }
        return users;
    }

    public void insert(Users users) {
        em.persist(users);
        em.flush();
    }

    public void delete(Integer userId) {
        Users users = em.find(Users.class, userId);
        em.remove(users);
        em.flush();
    }

    public void update(Users users) {
        em.merge(users);
        em.flush();
    }

    public Users find(Integer userId) {
        return em.find(Users.class, userId);
    }
}
