package api.tests;

import api.models.BoardInfo;
import api.models.Result;
import api.steps.BoardApiSteps;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class BoardApiTests {

    @Test

    public void checkBoardApi() {
        BoardApiSteps boardApiSteps = new BoardApiSteps();
        Result<List<BoardInfo>> boardInfoResult = boardApiSteps.getBoardForProject(2);
        Assert.assertTrue(boardInfoResult.getResult().size()>0, "Board request doesn't contain records");
        System.out.println(boardInfoResult.getResult().get(0).getName());
    }
}
