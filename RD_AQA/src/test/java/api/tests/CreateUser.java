package api.tests;

import api.models.Result;
import api.models.args.BodyArgs;
import api.steps.BaseApiSteps;
import api.steps.UserApiSteps;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static api.enums.UserRoles.USER;
import static api.methods.Users.CREATE_USER;
import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;


public class CreateUser extends BaseApiSteps {

    static final String TEST_USER = "test_user_ab";
    static final String TEST_USER_PASS = "test_pass_ab";
    static final String TEST_PROJECT_NAME = "testABC";
    static final String TEST_PROJECT_DESCRIPTION = "Super Test Project";
    static final String CREATE_PROJECT = "createProject";
    static final String ADD_PROJECT_USER = "addProjectUser";
    static int TEST_USER_ID;
    static int TEST_PROJECT_ID;
    UserApiSteps userAPI = new UserApiSteps();

    @Test
    public void test1_createUser() {

        String response = userAPI.createUser(TEST_USER, TEST_USER_PASS);

        if (response != "false") {
            try {
                int id = Integer.parseInt(response);
                System.out.println("ID : " + id);
                TEST_USER_ID = id;

            } catch (NumberFormatException e) {
                System.err.println("Invalid user id: " + response);
            }
        }
    }

    @Test
    public void test2_createProject() {

        api.models.args.projects.CreateProject args = api.models.args.projects.CreateProject.builder()
                .name(TEST_PROJECT_NAME)
                .description(TEST_PROJECT_DESCRIPTION)
                .owner_id(TEST_USER_ID)
                .build();


        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(CREATE_PROJECT)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);
        response.then().statusCode(200);
        Result result = response.as(Result.class);

        response.prettyPrint();

        String res = result.getResult().toString();

        if (res != "false") {
            try {
                int id = Integer.parseInt(res);
                System.out.println("PROJECT ID : " + id);
                TEST_PROJECT_ID = id;

            } catch (NumberFormatException e) {
                System.err.println("Invalid project id: " + res);
            }
        }

    }

    @Test
    public void test3_addProjectUser() {

        api.models.args.projects.AddProjectUser args = api.models.args.projects.AddProjectUser.builder()
                .project_id(TEST_PROJECT_ID)
                .user_id(TEST_USER_ID)
                .build();


        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method(ADD_PROJECT_USER)
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);

        // response.prettyPrint();

        System.out.println("ADD USER TO PROJECT");

    }


    public int getId() {
        return TEST_USER_ID;
    }

    public String getProjectName() {
        return TEST_PROJECT_NAME;
    }

    public int getProjectId() {
        return TEST_PROJECT_ID;
    }

    public String getUser() {
        return TEST_USER;
    }

    public String getPass() {
        return TEST_USER_PASS;
    }

}

