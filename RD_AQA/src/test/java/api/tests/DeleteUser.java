package api.tests;

import api.models.Result;
import api.models.args.BodyArgs;
import api.steps.BaseApiSteps;
import api.steps.UserApiSteps;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static utils.EnvProperties.API_TOKEN;
import static utils.EnvProperties.API_USERNAME;

public class DeleteUser extends BaseApiSteps {

    CreateUser cu = new CreateUser();
    UserApiSteps userAPI = new UserApiSteps();

    @Test
    public void test1_deleteProject() {

        api.models.args.projects.DeleteProject args = api.models.args.projects.DeleteProject.builder()
                .project_id(cu.getProjectId())
                .build();


        BodyArgs bodyArgs = BodyArgs.builder()
                .params(args)
                .method("removeProject")
                .build();

        Response response = postRequest(API_USERNAME, API_TOKEN, bodyArgs);

        System.out.println("Delete project : " + cu.getProjectId());
    }

    @Test
    public void test2_deleteUser() {
        CreateUser cu = new CreateUser();
        int id = cu.getId();

        Boolean response = userAPI.deleteUser(String.valueOf(id));

        System.out.println("Delete account : " + response);

    }

}

