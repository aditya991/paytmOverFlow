package com.paytm.dal;

import com.paytm.configuration.DBConfiguration;
import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.InterestRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class InterestDalImpl {

    private static final Logger LOG = LoggerFactory.getLogger(DBConfiguration.class);

    @Autowired
    private EntityManagerFactory emf3;

    @Autowired
    private InterestRepo interestRepo;

    @Autowired
    private DeptRepo deptRepo;

    public InterestDalImpl() {}

    public boolean insertInterestMethod(Integer u_id, Integer dept_id, EntityManagerFactory EMF2)
    {
        //LOG.info("inside insertInterestMethod-------1");
        //return true;
        //try
        //{
            LOG.info("inside insert InterestMethod");
            Interest i = new Interest(u_id, dept_id);
           // i.setCreated(new Date());
          //  i.setUpdated(new Date());
            emf3=EMF2;
            EntityManager em = emf3.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            em.close();
            return true;
     /*   }
        catch (Exception e)
        {
           // LOG.info(e);
            return false;
        }*/
    }

    public boolean deleteInterestMethod(Integer u_id, Integer dept_id) {
       try {
           interestRepo.removeInterestByUIdByDeptId(u_id,dept_id);
           LOG.info("Inside deleteInterestMethod OwnDept");
           return true;
          } catch (Exception e) {
           System.out.println(e);
            return false;
          }
    }

    public List<String> getUserAllInterestNamesMethod(Integer u_id) {

        LOG.info("inside showAllInterestMethod ");

        List<Integer> deptIdList = interestRepo.getDeptIdbyUId(u_id);
        List<String> deptNameList = new ArrayList<>();

        EntityManager em = emf3.createEntityManager();
        Iterator iterator = deptIdList.iterator();

        while (iterator.hasNext()) {

            Dept dpt = em.find(Dept.class, (Integer)iterator.next());
            LOG.info(dpt.getDept_name());
            deptNameList.add(dpt.getDept_name());
        }
        return deptNameList;
    }

    public List<Dept> getUserAllInterestMethod(Integer u_id) {

        LOG.info("inside showAllInterestMethod ");

        List<Integer> deptIdList = interestRepo.getDeptIdbyUId(u_id);
        List<Dept> deptList = new ArrayList<>();

        EntityManager em = emf3.createEntityManager();
        Iterator iterator = deptIdList.iterator();

        while (iterator.hasNext()) {

            Dept dpt = em.find(Dept.class, (Integer)iterator.next());
            deptList.add(dpt);
        }
        return deptList;
    }
}