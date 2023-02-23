package com.vitali.mapping;

import com.vitali.mapping.entity.Employee;
import com.vitali.mapping.entity.EmployeeTask;
import com.vitali.mapping.entity.EmployeeTask_;
import com.vitali.mapping.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HibernateRunner {
    public static void main(String[] args) {
        // прежде чем указать сущность company в билдере User, нужно ее создать
//        Company company = Company.builder()
//                .name("Apple")
//                .build();

//        User user = User.builder()
//                .lastname("Jack")
//                .firstname("Johnson")
//                .username("jack")
//                .company(company)
//                .build();

//        Employee employee = Employee.builder()
//                .name("John")
//                .occupation("Seller")
//                .salary(2000)
//                .join(new Date(2020, Calendar.APRIL, 6))
//                .build();
//
//        EmployeeTask employeeTask = EmployeeTask.builder()
//                .description("Must be done")
//                .deadline(new Date(2023, Calendar.APRIL, 12))
//                .employee(employee)
//                .build();

        EntityManager em = HibernateUtil.getEntityManager();
        em.getTransaction().begin();


        Date curdate = new Date();
//        String name = "Петров Петр";
//        String taskName = "Купить кофе";

        CriteriaBuilder builder = em.getCriteriaBuilder();

        CriteriaQuery<Tuple> criteria = builder.createQuery(Tuple.class);
        Root<EmployeeTask> employeeTaskRoot = criteria.from(EmployeeTask.class);
//        Join<Object, Object> employee = employeeTaskRoot.join("employee");

//        Path<String> name = employeeTaskRoot.get("name");
        Path<Integer> id = employeeTaskRoot.get(EmployeeTask_.ID);
        Path<Employee> employeePath = employeeTaskRoot.get(EmployeeTask_.EMPLOYEE);
//        Path<String> description = employeeTaskRoot.get("description");
        criteria.multiselect(id, employeePath);
        criteria.where(builder.lessThan(employeeTaskRoot.get(EmployeeTask_.DEADLINE), curdate)).distinct(true);

        List<Tuple> tuples = em.createQuery(criteria).getResultList();
        for (Tuple tuple : tuples) {
            Integer i = tuple.get(id);
            Employee employee = tuple.get(employeePath);
//            String s = tuple.get(name);
//            String d = tuple.get(description);
//            Date i = tuple.get(deadline);
            System.out.println(i + " : " + employee);
        }
        em.getTransaction().commit();
        em.close();
    }
}
