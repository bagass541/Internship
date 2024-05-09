package com.bagas;

import com.bagas.db.ConnectionPool;
import com.bagas.entities.Computer;
import com.bagas.entities.Employee;
import com.bagas.entities.Game;
import com.bagas.entities.Location;
import com.bagas.entities.Position;
import com.bagas.entities.Reservation;
import com.bagas.entities.Session;
import com.bagas.entities.TypeComputer;
import com.bagas.entities.User;
import com.bagas.mappers.ComputerMapper;
import com.bagas.mappers.EmployeeMapper;
import com.bagas.mappers.GameMapper;
import com.bagas.mappers.LocationMapper;
import com.bagas.mappers.PositionMapper;
import com.bagas.mappers.ReservationMapper;
import com.bagas.mappers.SessionMapper;
import com.bagas.mappers.UserMapper;
import com.bagas.repositories.impl.ComputerRepositoryImp;
import com.bagas.repositories.impl.EmployeeRepositoryImp;
import com.bagas.repositories.impl.GameRepositoryImp;
import com.bagas.repositories.impl.LocationRepositoryImp;
import com.bagas.repositories.impl.PositionRepositoryImp;
import com.bagas.repositories.impl.ReservationRepositoryImp;
import com.bagas.repositories.impl.SessionRepositoryImp;
import com.bagas.repositories.impl.UserRepositoryImp;
import com.bagas.services.ComputerService;
import com.bagas.services.EmployeeService;
import com.bagas.services.ReservationService;
import com.bagas.services.SessionService;
import com.bagas.services.UserService;
import com.bagas.util.PropertyReader;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Properties;

public class ComputerClub {

    private static final String URL_PROPERTY = "url";

    private static final String USERNAME_PROPERTY = "username";

    private static final String PASSWORD_PROPERTY = "password";

    public static void main(String[] args) {
        try {
            PropertyReader reader = new PropertyReader();
            Properties properties = reader.readConnectionProperties();

            ConnectionPool connectionPool = ConnectionPool.create(properties.getProperty(URL_PROPERTY),
                    properties.getProperty(USERNAME_PROPERTY), properties.getProperty(PASSWORD_PROPERTY));

            ComputerService computerService = new ComputerService(new ComputerRepositoryImp(connectionPool),
                    new LocationRepositoryImp(connectionPool));

            EmployeeService employeeService = new EmployeeService(new EmployeeRepositoryImp(connectionPool),
                    new PositionRepositoryImp(connectionPool));

            ReservationService reservationService = new ReservationService(
                    new ReservationRepositoryImp(connectionPool));

            SessionService sessionService = new SessionService(new GameRepositoryImp(connectionPool),
                    new SessionRepositoryImp(connectionPool));

            UserService userService = new UserService(new UserRepositoryImp(connectionPool));

            printGetAll(computerService, employeeService, reservationService, sessionService, userService);

            printGetById(computerService, employeeService, reservationService, sessionService, userService);

            printInnerJoins(computerService, sessionService);

            creationTest(computerService, employeeService, reservationService, sessionService, userService);

            printGetAll(computerService, employeeService, reservationService, sessionService, userService);

            updateTest(computerService, employeeService, reservationService, sessionService, userService);

            printGetAll(computerService, employeeService, reservationService, sessionService, userService);

            deletingTest(computerService, employeeService, reservationService, sessionService, userService);

            printGetAll(computerService, employeeService, reservationService, sessionService, userService);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printInnerJoins(ComputerService computerService, SessionService sessionService) {

        System.out.println("| GET COMPUTERS BY ROOM |");

        computerService.getByRoom(101).forEach(System.out::println);
        printSeparator();

        System.out.println("| GET SESSIONS BY USERNAME |");

        sessionService.getSessionsByUserName("Кирилл").forEach(System.out::println);
        printSeparator();

        System.out.println("| GET THE TOP 3 POPULAR GAMES |");

        sessionService.getTheMostPopularGame().forEach(System.out::println);
        printSeparator();
    }

    private static void printGetById(ComputerService computerService, EmployeeService employeeService,
                                     ReservationService reservationService, SessionService sessionService,
                                     UserService userService) throws ClassNotFoundException {

        System.out.println("| GET BY ID |");

        System.out.println(computerService.getLocationById(101L));
        printSeparator();

        System.out.println(computerService.getComputerById(101L));
        printSeparator();

        System.out.println(employeeService.getPositionById(101L));
        printSeparator();

        System.out.println(employeeService.getEmployeeById(101L));
        printSeparator();

        System.out.println(reservationService.getById(101L));
        printSeparator();

        System.out.println(sessionService.getGameById(101L));
        printSeparator();

        System.out.println(sessionService.getSessionById(101L));
        printSeparator();

        System.out.println(userService.getById(101L));
        printSeparator();
    }

    private static void printGetAll(ComputerService computerService, EmployeeService employeeService,
                                    ReservationService reservationService, SessionService sessionService,
                                    UserService userService) {

        System.out.println("| GET ALL |");

        computerService.getAllLocations().forEach(System.out::println);
        printSeparator();
        computerService.getAllComputers().forEach(System.out::println);

        printSeparator();

        employeeService.getAllPositions().forEach(System.out::println);
        printSeparator();
        employeeService.getAllEmployees().forEach(System.out::println);

        printSeparator();

        reservationService.getAll().forEach(System.out::println);

        printSeparator();

        sessionService.getAllGames().forEach(System.out::println);
        printSeparator();
        sessionService.getAllSessions().forEach(System.out::println);

        printSeparator();

        userService.getAll().forEach(System.out::println);
        printSeparator();
    }

    private static void creationTest(ComputerService computerService, EmployeeService employeeService,
                                     ReservationService reservationService, SessionService sessionService,
                                     UserService userService) {

        System.out.println("| CREATION |");

        Location location = LocationMapper.createLocation(106L, 101, 6);
        Computer computer = ComputerMapper.createComputer(106L, TypeComputer.MID, 106L);

        computerService.createLocation(location);
        computerService.createComputer(computer);

        Position position = PositionMapper.createPosition(105L, "Инженер", BigDecimal.valueOf(1200));
        Employee employee = EmployeeMapper.createEmployee(105L, "Артем", "Старостенко",
                LocalDate.of(2003, 7, 12), 105L, "+37525645454");

        employeeService.createPosition(position);
        employeeService.createEmployee(employee);

        Reservation reservation = ReservationMapper.createReservation(105L, 101L, 106L,
                LocalDate.of(2024, 5, 15), LocalTime.of(11, 0));

        reservationService.create(reservation);

        Game game = GameMapper.createGame(106L, "Шутер", "Battlefield 1");
        Session session = SessionMapper.createSession(101L, 101L, 106L, 106L,
                106L, LocalTime.of(1, 0));

        sessionService.createGame(game);
        sessionService.createSession(session);

        User user = UserMapper.createUser(105L, "Влада", "Диденко",
                LocalDate.of(2003, 2, 16), "+3752675656");

        userService.create(user);
    }

    private static void updateTest(ComputerService computerService, EmployeeService employeeService,
                                   ReservationService reservationService, SessionService sessionService,
                                   UserService userService) {

        System.out.println("| UPDATE |");

        Location location = LocationMapper.createLocation(106L, 101, 8);
        Computer computer = ComputerMapper.createComputer(106L, TypeComputer.HIGH, 106L);

        computerService.updateLocation(location);
        computerService.updateComputer(computer);

        Position position = PositionMapper.createPosition(105L, "Инженер (2)", BigDecimal.valueOf(1300));
        Employee employee = EmployeeMapper.createEmployee(105L, "Артем (2)", "Старостенко (2)",
                LocalDate.of(2003, 7, 12), 105L, "+37525645454 (2)");

        employeeService.updatePosition(position);
        employeeService.updateEmployee(employee);

        Reservation reservation = ReservationMapper.createReservation(105L, 102L, 102L,
                LocalDate.of(2024, 5, 15), LocalTime.of(11, 0));

        reservationService.update(reservation);

        Game game = GameMapper.createGame(106L, "Шутер (2)", "Battlefield 1 (2)");
        Session session = SessionMapper.createSession(105L, 101L, 106L, 103L,
                106L, LocalTime.of(1, 0));

        sessionService.updateGame(game);
        sessionService.updateSession(session);

        User user = UserMapper.createUser(105L, "Влада (2)", "Диденко (2)",
                LocalDate.of(2003, 2, 16), "+3752675656 (2)");

        userService.update(user);
    }

    private static void deletingTest(ComputerService computerService, EmployeeService employeeService,
                                     ReservationService reservationService, SessionService sessionService,
                                     UserService userService) {

        System.out.println("| DELETING |");

        userService.deleteById(105L);

        reservationService.deleteById(105L);

        sessionService.deleteSessionById(105L);
        sessionService.deleteGameById(106L);

        computerService.deleteComputerById(106L);
        computerService.deleteLocationById(106L);

        employeeService.deleteEmployeeById(105L);
        employeeService.deletePositionById(105L);
    }

    private static void printSeparator() {
        System.out.println();
        System.out.println("--------------------------");
        System.out.println();
    }
}
