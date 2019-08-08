package com.paytm.dal;

import com.paytm.entity.Dept;
import com.paytm.entity.Interest;
import com.paytm.repo.DeptRepo;
import com.paytm.repo.InterestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class InterestDalImpl {

    @Autowired
    private EntityManagerFactory emf;

    @Autowired
    private InterestRepo interestRepo;

    @Autowired
    private DeptRepo deptRepo;

    public boolean insertInterestMethod(Integer u_id, Integer dept_id)
    {
        System.out.println("inside insertInterestMethod-------1");
        return true;
//        try
//        {
//
//            System.out.println("inside insert InterestMethod");
//            Interest i = new Interest(u_id, dept_id);
//            i.setCreated(new Date());
//            i.setUpdated(new Date());
//            EntityManager em = emf.createEntityManager();
//            EntityTransaction tx = em.getTransaction();
//            em.getTransaction().begin();
//            em.persist(i);
//            em.getTransaction().commit();
//            em.close();
//            return true;
//        }
//        catch (Exception e)
//        {
//            System.out.println(e);
//            return false;
//        }
    }

    public boolean deleteInterestMethod(Integer u_id, Integer dept_id) {
      //  try {

            Interest i = interestRepo.getInterestByUIdByDeptId(String.valueOf(u_id),
                    String.valueOf(dept_id));

           // if (!i.isOwnDept()) {
                i.setUpdated(new Date());
                EntityManager em = emf.createEntityManager();
                EntityTransaction tx = em.getTransaction();
                em.getTransaction().begin();
                em.remove(i);
                em.getTransaction().commit();
               em.close();
              /*  return true;
            } else
                return false;


        } catch (Exception e) {
            return false;

        }*/
              return true;
    }

    public List<String> showAllInterestMethod(Integer u_id) {
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