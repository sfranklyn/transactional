/*
 * Copyright 2014 Samuel Franklyn <sfranklyn at gmail.com>.
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

import java.util.Date;
import java.util.logging.Logger;
import org.apache.deltaspike.cdise.api.CdiContainer;
import org.apache.deltaspike.cdise.api.CdiContainerLoader;
import org.apache.deltaspike.core.api.provider.BeanProvider;

/**
 *
 * @author Samuel Franklyn <sfranklyn at gmail.com>
 */
public class Main {

    private static final Logger log = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        CdiContainer container = CdiContainerLoader.getCdiContainer();
        container.boot();

        UsersDaoBean usersDaoBean = BeanProvider.getContextualReference(UsersDaoBean.class, false);

        Users users = usersDaoBean.selectByUserName("sf");
        if (users != null) {
            log.info(users.toString());
            usersDaoBean.delete(users.getUserId());
        }

        users = new Users();
        users.setUserName("sf");
        users.setUserPassword("password");
        users.setUserFullName("Samuel Franklyn");
        users.setUserLastPwdChange(new Date());
        usersDaoBean.insert(users);
        log.info(users.toString());

        users.setUserPhone("007");
        usersDaoBean.update(users);
        log.info(users.toString());

        container.shutdown();
    }

}
