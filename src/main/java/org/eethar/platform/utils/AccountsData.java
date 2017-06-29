package org.eethar.platform.utils;

import java.util.HashSet;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.eethar.platform.core.entity.Authority;
import org.eethar.platform.core.entity.User;

/**
 *
 * @author superyass
 */

@Startup
@Singleton
public class AccountsData {
    
    @PersistenceContext
    EntityManager em;
    
    @PostConstruct
    public void initData(){
        
        System.out.println("init accounts data started");
        
        Authority auth1 = new Authority("ROLE_ADMIN");
        Authority auth2 = new Authority("ROLE_USER");
        Authority auth3 = new Authority("ROLE_ANONYMOUS");
        
        em.merge(auth1);
        em.merge(auth2);
        em.merge(auth3);
        
        User admin = new User();
        admin.setId(1l);
        admin.setActivated(true);
        admin.setLogin("admin");
        admin.setPassword("21232f297a57a5a743894a0e4a801fc3");
        admin.setAuthorities(new HashSet<>());
        admin.getAuthorities().add(auth1);
        admin.getAuthorities().add(auth2);
        
        User user = new User();
        user.setId(2l);
        user.setActivated(true);
        user.setLogin("user");
        user.setPassword("ee11cbb19052e40b07aac0ca060c23ee");
        user.setAuthorities(new HashSet<>());
        user.getAuthorities().add(auth1);
        user.getAuthorities().add(auth2);
        
        em.merge(admin);
        em.merge(user);
        
        System.out.println("init accounts data finished");
    }
    
}
