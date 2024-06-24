package edu.shopify.util;

import edu.shopify.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//public class HibernateUtil {
//    private static SessionFactory session = createSession();
//
//    private static SessionFactory createSession() {
//        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
//                .configure("hibernate.cfg.xml")
//                .build();
//
//        Metadata metaData = new MetadataSources(build)
//                .addAnnotatedClass(SupplierEntity.class)
//                .addAnnotatedClass(EmployeeEntity.class)//can pass multiple entities
//                .addAnnotatedClass(CategoryEntity.class)
//                .addAnnotatedClass(ProductEntity.class)
//                .getMetadataBuilder()
//                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
//                .build();
//
//        return metaData.getSessionFactoryBuilder().build();
//    }
//
//    public static Session getSession(){
//        return session.openSession();
//    }
//}

public class HibernateUtil {
    private static SessionFactory sessionFactory = createSession();

    private static SessionFactory createSession() {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml")
                .build();

        Metadata metaData = new MetadataSources(build)
                .addAnnotatedClass(SupplierEntity.class)
                .addAnnotatedClass(EmployeeEntity.class)
                .addAnnotatedClass(CategoryEntity.class)
                .addAnnotatedClass(ProductEntity.class)
                .addAnnotatedClass(CustomerEntity.class)
                .addAnnotatedClass(OrderEntity.class)
                .addAnnotatedClass(OrderProductEntity.class)
                .getMetadataBuilder()
                .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
                .build();

        return metaData.getSessionFactoryBuilder().build();
    }

    public static Session getSession(){
        return sessionFactory.openSession();
    }
}

