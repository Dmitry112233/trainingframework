package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import io.qameta.allure.model.Status;
import io.qameta.allure.model.StepResult;

import java.util.UUID;

public class AllureUtil {

    String stepUuid;
    StepResult stepResult;
    AllureLifecycle lifecycle;

    public void startStep(String stepName){
        lifecycle = Allure.getLifecycle();
        stepUuid = UUID.randomUUID().toString();
        stepResult = new StepResult().withName(stepName);
        lifecycle.startStep(stepUuid, stepResult);
    }

    public void setFailedStatus(){
        lifecycle.updateStep(stepUuid, s -> s
                .withStatus(Status.FAILED));
    }

    public void setPassedStatus(){
        lifecycle.updateStep(stepUuid, s -> s
                .withStatus(Status.PASSED));
    }

    public void finishStep(){
        lifecycle.stopStep(stepUuid);
    }
}
