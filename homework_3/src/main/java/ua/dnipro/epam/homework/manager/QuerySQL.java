package ua.dnipro.epam.homework.manager;

public class QuerySQL {

    //User query
    public static final String INSERT_USERS_LOGIN = "INSERT INTO user (username, password, name, surname, status, role_id) VALUES (?,?,?,?,?,?)";

    public static final String SELECT_FROM_USER_BY_ID = "SELECT user.id, user.username, user.password,user.name, user.surname, user.status, roles.id_roles , roles.role " +
            " FROM  user INNER JOIN  roles ON roles.id_roles = user.role_id WHERE user.id = ?";

    public static final String SELECT_FROM_USER_BY_USERNAME = "SELECT user.id, user.username, user.password,user.name, user.surname, user.status, roles.id_roles , roles.role " +
            " FROM  user INNER JOIN  roles ON roles.id_roles = user.role_id WHERE user.username = ?";

    public static final String SELECT_FROM_USER_ALL_WHERE_STATUS_TRUE = "SELECT u.id, u.username, u.password, u.name, u.surname, u.status, r.id_roles as id, r.role as role from user as u \n" +
            "inner JOIN  roles as r on u.role_id = r.id_roles where r.id_roles = 2 and u.status = 1";

    public static final String SELECT_FROM_USER_ALL_WHERE_STATUS_FALSE = "SELECT u.id, u.username, u.password, u.name, u.surname, u.status, r.id_roles as id, r.role as role from user as u \n" +
            "inner JOIN  roles as r on u.role_id = r.id_roles where r.id_roles = 2 and u.status = 0";

    public static final String UPDATE_USER_STATUS = "UPDATE user SET status = ? WHERE user.id = ?";

    public static final String SELECT_FROM_USER_ALL = "SELECT u.id, u.username, u.password, u.name, u.surname, u.status, r.id_roles as id, r.role as role from user as u " +
            " inner JOIN  roles as r on u.role_id = r.id_roles where r.id_roles = 2";

    public static final String UPDATE_USER_WHERE_ID = "UPDATE user SET username = ?, password = ?, name = ?, surname = ? WHERE user.id = ?";

    //Roles query
    public static final String SELECT_FROM_ROLES_BY_ROLE = "SELECT * from roles WHERE role = ?";


    //Test query
    public static final String SELECT_FROM_TEST_ALL = "SELECT t.id, t.name, t.time, t.number_of_questions, t.id_subject, t.id_complexity from test as t " +
            "JOIN subject as s on t.id_subject = s.id JOIN complexity as c on t.id_complexity = c.id";

    public static final String SELECT_FROM_TEST_BY_ID = "SELECT t.id, t.name, t.time, t.number_of_questions, t.id_subject, t.id_complexity from test as t " +
            "JOIN subject as s on t.id_subject = s.id JOIN complexity as c on t.id_complexity = c.id WHERE t.id = ?";

    public static final String INSERT_TEST_CREATE = "insert into test (name, time, number_of_questions, id_subject, id_complexity) values (?,?,?,?,?)";

    public static final String DELETE_FROM_TEST_WHERE_ID = "DELETE from test where id = ?";

    public static final String DELETE_FROM_TEST_WHERE_NAME = "DELETE from test where name = ?";

    public static final String UPDATE_TEST_WHERE_ID = "update user set name = ?, time = ? where id = ?";


    //Subject
    public static final String SELECT_FROM_SUBJECT_NAME = "SELECT * from subject WHERE name = ? ";

    public static final String SELECT_ALL_FROM_SUBJECT = "SELECT * from subject";

    public static final String SELECT_FROM_SUBJECT_WHERE_ID = "SELECT * from subject where id = ?";

    public static final String SELECT_ALL_FROM_SUBJECT_RU = "SELECT * from subject_ru";


    //Complexity
    public static final String SELECT_ALL_FROM_COMPLEXITY = "SELECT * from complexity";

    public static final String SELECT_FROM_COMPLEXITY_WHERE_ID = "SELECT * from complexity where id = ?";

    //Question
    public static final String INSERT_QUESTION_CREATE = "insert into question (content, counter_question, number_of_answer, test_id) values (?,?,?,?)";

    public static final String DELETE_FROM_QUESTION_WHERE_TEST_ID = "DELETE from question where test_id = ?";

    public static final String SELECT_FROM_QUESTION_WHERE_TEST_ID = "select question.id, question.content, question.counter_question, question.number_of_answer, question.test_id " +
            "from question where question.test_id = ?";



    //Answer
    public static final String INSERT_ANSWER_CREATE = "insert into answer (variant, counter_answer, correct_answer, question_id, test_id) values (?,?,?,?,?)";

    public static final String SELECT_FROM_ANSWER_WHERE_QUESTION_ID = "SELECT a.id, a.variant, a.counter_answer, a.correct_answer, a.question_id, a.test_id " +
            "from answer as a WHERE question_id = ?";

    public static final String DELETE_FROM_ANSWER_WHERE_TEST_ID = "DELETE from answer where test_id = ?";

    //Grade
    public static final String INSERT_GRADE_CREATE= "insert into grade (result, test_id, user_id) values (?,?,?)";

    public static final String SELECT_FROM_GRADE_WHERE_USER_ID = "select * from grade where user_id = ?";
}
