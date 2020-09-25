package org.teachmeskills.project;


import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.teachmeskills.project.application.actions.*;
import org.teachmeskills.project.application.actions.authorisationActions.AuthorisationAction;
import org.teachmeskills.project.application.actions.updateActions.*;
import org.teachmeskills.project.application.utils.Input;
import org.teachmeskills.project.controllers.UpdateController;
import org.teachmeskills.project.entitiy.City;
import org.teachmeskills.project.entitiy.Transport;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

@Configuration
@ComponentScan(value = "org.teachmeskills.project")
public class RootConfiguration {

//    @Bean
//    public ComboPooledDataSource dataSource() {
//        ComboPooledDataSource dataSource = new ComboPooledDataSource();
//        try {
//            dataSource.setDriverClass("com.mysql.jdbc.Driver");
//            dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/transport_compani_db?serverTimezone=UTC");
//            dataSource.setUser("root");
//            dataSource.setPassword("my76sql423ol28eg28a_");
//
//            Properties properties = new Properties();
//            properties.setProperty("user", "root");
//            properties.setProperty("password", "my76sql423ol28eg28a_");
//            properties.setProperty("useUnicode", "true");
//            properties.setProperty("characterEncoding", "UTF8");
//            dataSource.setProperties(properties);
//
//            // set options:
//            dataSource.setMaxStatements(180);
//            dataSource.setMaxStatementsPerConnection(180);
//            dataSource.setMinPoolSize(50);
//            dataSource.setAcquireIncrement(10);
//            dataSource.setMaxPoolSize(60);
//            dataSource.setMaxIdleTime(30);
//        } catch (PropertyVetoException e) {
//            e.printStackTrace();
//        }
//        return dataSource;
//    }
//
//    @Scope(value = "prototype")
//    @Bean
//    public Connection connection(ComboPooledDataSource dataSource) {
//        try {
//             return dataSource.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }


    @Bean
    public Connection connection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/transport_compani_db?serverTimezone=UTC",
                    "root",
                    "my76sql423ol28eg28a_");
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    // City factory:......................
    @Bean
    public Supplier<City> idFieldCityFactory() {
        return () -> new City(Input.getInt("Введите ID:"));
    }

    @Bean
    public Supplier<City> allFieldsCityFactory() {
        return () -> new City(
                Input.getInt("Введите ID:"),
                Input.getString("Введите название города:"),
                Input.getDouble("Введите координату долготы:"),
                Input.getDouble("Введите координату широты:"),
                Input.getBoolean("Есть ли в городе аэропорт:"),
                Input.getBoolean("Есть ли в городе морской порт:"));
    }


    // Transport factory:......................
    @Bean
    public Supplier<Transport> idFieldTransportFactory() {
        return () -> new Transport(Input.getInt("Введите ID:"));
    }

    @Bean
    public Supplier<Transport> allFieldsTransportFactory() {
        return () -> new Transport(
                Input.getInt("Введите ID:"),
                Input.getString("Введите название транспортного средства:"),
                Input.getInt("Введите скорость (км/ч):"),
                Input.getInt("Введите количество перевозимых пасажиров (чел.):"),
                Input.getInt("Введите количество перевозимого груза (кг):"),
                Input.getTypeTransport(),
                Input.getInt("Введите стоимость услуги пользования данным транспортом (руб/км):"));
    }




    // Generators of Actions-maps:........................
    @Bean
    public Map<Integer, UpdateAction> cityUpdateActions(@Qualifier(value = "citiesUpdateController") UpdateController<City> controller,
                                                          @Qualifier(value = "allFieldsCityFactory") Supplier<City> allFieldsFactory,
                                                          @Qualifier(value = "idFieldCityFactory") Supplier<City> idFieldFactory) {
        Map<Integer, UpdateAction> actions = new HashMap<>();
        actions.put(1, new GetAllAction<>(controller));
        actions.put(2, new AddOneAction<>(controller, allFieldsFactory));
        actions.put(3, new UpdateOneAction<>(controller, allFieldsFactory));
        actions.put(4, new RemoveOneAction<>(controller, idFieldFactory));
        actions.put(0, new ComeBackApplicationAction());
        return actions;
    }

    @Bean
    public Map<Integer, UpdateAction> transportUpdateActions(@Qualifier(value = "transportUpdateController") UpdateController<Transport> controller,
                                                          @Qualifier(value = "allFieldsTransportFactory") Supplier<Transport> allFieldsFactory,
                                                          @Qualifier(value = "idFieldTransportFactory") Supplier<Transport> idFieldFactory) {
        Map<Integer, UpdateAction> actions = new HashMap<>();
        actions.put(1, new GetAllAction<>(controller));
        actions.put(2, new AddOneAction<>(controller, allFieldsFactory));
        actions.put(3, new UpdateOneAction<>(controller, allFieldsFactory));
        actions.put(4, new RemoveOneAction<>(controller, idFieldFactory));
        actions.put(0, new ComeBackApplicationAction());
        return actions;
    }

    @Bean
    Map<Integer, AuthorisationAction> authorisationActions(AuthorisationAction checkAccountAction,
                                                           AuthorisationAction createAccountAction,
                                                           AuthorisationAction stopApplicationFromAuthorisationAction) {
        Map<Integer, AuthorisationAction> actions = new HashMap<>();
        actions.put(0, stopApplicationFromAuthorisationAction);
        actions.put(1, checkAccountAction);
        actions.put(2, createAccountAction);
        return actions;
    }

    @Bean
    Map<Integer, CommonAction> administratorsActions(CommonAction exportQueriesResponsesLogXMLAction,
                                                     CommonAction findOptimalTransportAction,
                                                     CommonAction stopApplicationFromCommonAction,
                                                     CommonAction updateCitiesAction,
                                                     CommonAction updateTransportAction) {
        Map<Integer, CommonAction> actions = new HashMap<>();
        actions.put(0, stopApplicationFromCommonAction);
        actions.put(1, findOptimalTransportAction);
        actions.put(2, updateCitiesAction);
        actions.put(3, updateTransportAction);
        actions.put(4, exportQueriesResponsesLogXMLAction);
        return actions;
    }

    @Bean
    Map<Integer, CommonAction> usersActions(CommonAction findOptimalTransportAction,
                                            CommonAction stopApplicationFromCommonAction) {
        Map<Integer, CommonAction> actions = new HashMap<>();
        actions.put(0, stopApplicationFromCommonAction);
        actions.put(1, findOptimalTransportAction);
        return actions;
    }
}
