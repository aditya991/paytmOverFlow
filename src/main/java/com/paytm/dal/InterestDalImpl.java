package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class InterestDalImpl implements InterestDal {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private InterestRepo interestRepo;

    @Autowired
    private DeptRepo deptRepo;

    @Override
    public boolean insertInterest(Integer u_id, Integer dept_id) {
        try {
            Interest i = new Interest(u_id, dept_id);
            i.setCreated(new Date());
            i.setUpdated(new Date());
            EntityManager em = emf.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            em.getTransaction().begin();
            em.persist(i);
            em.getTransaction().commit();
            em.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteInterest(Integer u_id, Integer dept_id) {
        try {

            Interest i = interestRepo.getInterestByUIdByDeptId(String.valueOf(u_id),
                    String.valueOf(dept_id));

            if (!i.isOwnDept()) {
                i.setUpdated(new Date());
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                em.getTransaction().begin();
                em.remove(i);
                em.getTransaction().commit();
                em.close();
                return true;
            } else
                return false;
            //System.out.println("User can't remove his/her own department as an interest.");

        } catch (Exception e) {
            return false;
            //System.out.println("Interest matching the user details Not found.");
        }
    }

    @Override
    public List<String> showAllInterest(Integer u_id) {
        List<Integer> deptIdList = interestRepo.getDeptIdbyUId(String.valueOf(u_id));
        List<String> deptNameList = null;

        EntityManager em = emf.createEntityManager();

        Iterator iterator = deptIdList.iterator();
        while (iterator.hasNext()) {
            Dept dpt = em.find(Dept.class, iterator.next());
            deptNameList.add(dpt.getDept_name());
        }

        return deptNameList;
    }
}